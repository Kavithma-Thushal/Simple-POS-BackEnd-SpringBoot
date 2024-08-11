let placeOrderUrl = "http://localhost:8080/api/v1/orders";

loadAllOrders();

$("#btnPlaceOrder").click(function () {

    let orderId = $("#txtPlaceOrderOrderId").val();
    let customerId = $("#txtPlaceOrderCustomerId").val();
    let itemCode = $("#txtPlaceOrderItemCode").val();
    let buyQty = $("#txtPlaceOrderBuyQty").val();
    let total = $("#txtPlaceOrderTotal").val();

    let orderObj = {
        orderId: orderId,
        customerId: customerId,
        orderDetailsList: [
            {
                itemCode: itemCode,
                buyQty: buyQty,
                total: total
            }
        ]
    };

    $.ajax({
        url: placeOrderUrl + "/placeOrder",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(orderObj),
        success: function (res) {
            loadAllOrders();
            alert("Order Placed Successfully...!");
        },
        error: function (error) {
            alert("Order Place Error...!");
        }
    });
});

$("#btnLoadAllOrders").click(function () {
    loadAllOrders();
});

function loadAllOrders() {
    $('#orderTable').empty();

    $.ajax({
        url: placeOrderUrl + "/loadAllOrders",
        method: "GET",
        success: function (res) {
            res.forEach(order => {
                let orderId = order.orderId;
                let customerId = order.customerId;
                let itemCode = order.orderDetailsList[0].itemCode;
                let buyQty = order.orderDetailsList[0].buyQty;
                let total = order.orderDetailsList[0].total;

                let row = `<tr>
                    <td>${orderId}</td>
                    <td>${customerId}</td>
                    <td>${itemCode}</td>
                    <td>${buyQty}</td>
                    <td>${total}</td>
                </tr>`;

                $("#orderTable").append(row);
            });
            placeOrderTableListener();
            clearPlaceOrderInputs();
        },
        error: function (error) {
            console.log("Load All Orders Error...!");
        }
    });
}

function placeOrderTableListener() {
    $("#orderTable>tr").on("click", function () {
        let orderId = $(this).children().eq(0).text();
        let customerId = $(this).children().eq(1).text();
        let itemCode = $(this).children().eq(2).text();
        let buyQty = $(this).children().eq(3).text();
        let total = $(this).children().eq(4).text();

        $("#txtPlaceOrderOrderId").val(orderId);
        $("#txtPlaceOrderCustomerId").val(customerId);
        $("#txtPlaceOrderItemCode").val(itemCode);
        $("#txtPlaceOrderBuyQty").val(buyQty);
        $("#txtPlaceOrderTotal").val(total);
    });
}

function clearPlaceOrderInputs() {
    $("#txtPlaceOrderOrderId").val("");
    $("#txtPlaceOrderCustomerId").val("");
    $("#txtPlaceOrderItemCode").val("");
    $("#txtPlaceOrderBuyQty").val("");
    $("#txtPlaceOrderTotal").val("");
}