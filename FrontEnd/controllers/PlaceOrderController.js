/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:05 AM - 6/18/2024
 **/

const placeOrderUrl = "http://localhost:8080/api/v1/orders";

let cart = [];

$("#btnAddToCart").click(function () {

    let customerId = $("#txtPlaceOrderCustomerId").val();
    let itemCode = $("#txtPlaceOrderItemCode").val();
    let itemDescription = $("#txtPlaceOrderItemDescription").val();
    let unitPrice = parseFloat($("#txtPlaceOrderItemUnitPrice").val());
    let buyQty = parseInt($("#txtPlaceOrderBuyQty").val());
    let total = buyQty * unitPrice;

    if (!itemCode || !itemDescription || !unitPrice || !buyQty || isNaN(total) || !customerId) {
        errorNotification("Please fill all item details correctly");
        return;
    }

    let existingItem = cart.find(item => item.itemCode === itemCode && item.customerId === customerId);
    if (existingItem) {
        existingItem.buyQty += buyQty;
        existingItem.total = existingItem.buyQty * existingItem.unitPrice;
    } else {
        cart.push({
            customerId: customerId,
            itemCode: itemCode,
            itemDescription: itemDescription,
            unitPrice: unitPrice,
            buyQty: buyQty,
            total: total
        });
    }

    updateCartTable();
});

function updateCartTable() {

    let tableBody = $("#orderTable");
    tableBody.empty();

    let total = 0;
    cart.forEach(item => {
        let row = `<tr>
            <td>${item.customerId}</td> <!-- Display Customer ID -->
            <td>${item.itemDescription}</td>
            <td>${item.unitPrice.toFixed(2)}</td>
            <td>${item.buyQty}</td>
            <td>${item.total.toFixed(2)}</td>
            <td><button class="btn btn-outline-danger btn-sm" onclick="removeFromCart('${item.itemCode}', '${item.customerId}')">Remove</button></td>
        </tr>`;
        tableBody.append(row);
        total += item.total;
    });

    $("#txtPlaceOrderTotal").val(total.toFixed(2));
}

function removeFromCart(itemCode, customerId) {
    cart = cart.filter(item => item.itemCode !== itemCode || item.customerId !== customerId);
    updateCartTable();
}

$("#btnPlaceOrder").click(function () {

    let orderId = $("#txtPlaceOrderOrderId").val();
    let customerId = $("#txtPlaceOrderCustomerId").val();

    if (!orderId || !customerId || cart.length === 0) {
        errorNotification("Please fill all fields and add items to the cart");
        return;
    }

    let orderObj = {
        orderId: orderId,
        customerId: customerId,
        orderDetailsList: cart.map(item => ({
            itemCode: item.itemCode,
            buyQty: item.buyQty,
            total: item.total
        }))
    };

    $.ajax({
        url: placeOrderUrl + "/placeOrder",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(orderObj),
        success: function (res) {
            cart = [];  // Clear cart after successful order
            updateCartTable();
            successNotification(res.message);
        },
        error: function (error) {
            errorNotification(error.responseJSON.message);
        }
    });
});