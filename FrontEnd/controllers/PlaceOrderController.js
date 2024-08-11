let baseUrl = "http://localhost:8080/api/v1/orders";

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
            alert("Order Placed Successfully...!");
        },
        error: function (error) {
            alert("Order Place Error...!");
        }
    });
});