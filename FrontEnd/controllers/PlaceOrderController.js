/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:05 AM - 6/18/2024
 **/

let placeOrderUrl = "http://localhost:8080/api/v1/orders";

$("#btnPlaceOrder").click(function () {

    let orderId = $("#txtPlaceOrderOrderId").val();
    let customerId = $("#txtPlaceOrderCustomerId").val();
    let itemCode = $("#txtPlaceOrderItemCode").val();
    let buyQty = parseInt($("#txtPlaceOrderBuyQty").val());
    let total = parseFloat($("#txtPlaceOrderTotal").val());

    if (!orderId || !customerId || !itemCode || !buyQty || isNaN(total)) {
        errorNotification("Please Fill All Fields");
        return;
    }

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
            successNotification(res.message);
        },
        error: function (error) {
            errorNotification(error.responseJSON.message);
        }
    });
});