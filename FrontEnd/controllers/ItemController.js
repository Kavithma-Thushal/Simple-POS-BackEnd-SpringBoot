let itemUrl = "http://localhost:8080/api/v1/item";

loadAllItems();

$("#btnSaveItem").click(function () {

    let code = $("#txtItemCode").val();
    let description = $("#txtItemDescription").val();
    let unitPrice = $("#txtItemUnitPrice").val();
    let qtyOnHand = $("#txtItemQtyOnHand").val();

    let itemObj = {
        code: code,
        description: description,
        unitPrice: unitPrice,
        qtyOnHand: qtyOnHand
    };

    $.ajax({
        url: itemUrl + "/saveItem",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(itemObj),
        success: function (res) {
            loadAllItems();
            alert(res.message);
        },
        error: function (error) {
            loadAllItems();
            alert(error.responseJSON.message);
        }
    });
});

$("#btnSearchItem").click(function () {
    let searchCode = $("#txtSearchItem").val();

    $.ajax({
        url: itemUrl + "/searchItem/" + searchCode,
        method: "GET",
        success: function (res) {
            $("#itemTable").empty();
            let row = `<tr>
                    <td>${res.data.code}</td>
                    <td>${res.data.description}</td>
                    <td>${res.data.unitPrice}</td>
                    <td>${res.data.qtyOnHand}</td>
                </tr>`;
            $("#itemTable").append(row);

            itemTableListener();
            clearItemInputs();
            console.log(res.message);
        },
        error: function (error) {
            loadAllItems();
            alert(error.responseJSON.message);
        }
    });
});

$("#btnUpdateItem").click(function () {

    let code = $("#txtItemCode").val();
    let description = $("#txtItemDescription").val();
    let unitPrice = $("#txtItemUnitPrice").val();
    let qtyOnHand = $("#txtItemQtyOnHand").val();

    let itemObj = {
        code: code,
        description: description,
        unitPrice: unitPrice,
        qtyOnHand: qtyOnHand
    };

    $.ajax({
        url: itemUrl + "/updateItem",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(itemObj),
        success: function (res) {
            loadAllItems();
            alert(res.message);
        },
        error: function (error) {
            loadAllItems();
            alert(error.responseJSON.message);
        }
    });
});

$("#btnDeleteItem").click(function () {
    let deleteId = $("#txtItemCode").val();

    $.ajax({
        url: itemUrl + "/deleteItem/" + deleteId,
        method: "DELETE",
        success: function (res) {
            loadAllItems();
            alert(res.message);
        },
        error: function (error) {
            loadAllItems();
            alert(error.responseJSON.message);
        }
    });
});

$('#btnLoadAllItems').click(function () {
    loadAllItems();
});

function loadAllItems() {
    $.ajax({
        url: itemUrl + "/loadAllItems",
        method: "GET",
        success: function (res) {
            $('#itemTable').empty();

            res.data.forEach(item => {
                let code = item.code;
                let description = item.description;
                let unitPrice = item.unitPrice;
                let qtyOnHand = item.qtyOnHand;

                let row = `<tr>
                    <td>${code}</td>
                    <td>${description}</td>
                    <td>${unitPrice}</td>
                    <td>${qtyOnHand}</td>
                </tr>`;
                $("#itemTable").append(row);
            });
            itemTableListener();
            clearItemInputs();
            console.log(res.message);
        },
        error: function (error) {
            console.log(error.responseJSON.message);
        }
    });
}

function itemTableListener() {
    $("#itemTable>tr").on("click", function () {
        let code = $(this).children().eq(0).text();
        let description = $(this).children().eq(1).text();
        let unitPrice = $(this).children().eq(2).text();
        let qtyOnHand = $(this).children().eq(3).text();

        $("#txtItemCode").val(code);
        $("#txtItemDescription").val(description);
        $("#txtItemUnitPrice").val(unitPrice);
        $("#txtItemQtyOnHand").val(qtyOnHand);
    });
}

function clearItemInputs() {
    $("#txtSearchItem").val("");
    $("#txtItemCode").val("");
    $("#txtItemDescription").val("");
    $("#txtItemUnitPrice").val("");
    $("#txtItemQtyOnHand").val("");
}