let baseUrl = "http://localhost:8080/api/v1/orders";

loadAllOrders();

$("#btnPlaceOrder").click(function () {

    let orderId = $("#txtOrderId").val();
    let customerId = $("#txtCustomerId").val();
    let itemCode = $("#txtItemCode").val();
    let buyQty = $("#txtBuyQty").val();
    let total = $("#txtTotal").val();

    const orderObj = {
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
        url: baseUrl + "/placeOrder",
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
        url: baseUrl + "/loadAllOrders",
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
        },
        error: function (error) {
            console.log("Load All Orders Error...!");
        }
    });
}