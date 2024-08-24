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
            alert("Item Saved Successfully...!");
        }, error: function (error) {
            alert("Item Saved Error...!");
        }
    });
});

$("#btnSearchItem").click(function () {
    let searchCode = $("#txtItemCode").val();
    $.ajax({
        url: itemUrl + "/searchItem/" + searchCode,
        method: "GET",
        success: function (res) {
            $("#itemTable").empty();
            let row = "<tr><td>" + res.code + "</td><td>" + res.description + "</td><td>" + res.unitPrice + "</td><td>" + res.qtyOnHand + "</td></tr>";
            $("#itemTable").append(row);
            console.log("Item Searched Successfully...!");
        },
        error: function (error) {
            console.log("Item Searched Error...!");
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
            alert("Item Updated Successfully...!");
        },
        error: function (error) {
            alert("Item Updated Error...!");
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
            alert("Item Deleted Successfully...!");
        },
        error: function (error) {
            alert("Item Deleted Error...!");
        }
    });
});

$('#btnLoadAllItems').click(function () {
    loadAllItems();
});

function loadAllItems() {
    $('#itemTable').empty();

    $.ajax({
        url: itemUrl + "/loadAllItems",
        method: "GET",
        success: function (res) {
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
    $("#txtItemCode").val("");
    $("#txtItemDescription").val("");
    $("#txtItemUnitPrice").val("");
    $("#txtItemQtyOnHand").val("");
}