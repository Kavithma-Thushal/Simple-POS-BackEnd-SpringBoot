let baseUrl = "http://localhost:8080/api/v1/item";

loadAllItems();

$("#btnSaveItem").click(function () {
    let code = $("#txtItemCode").val();
    let description = $("#txtItemDescription").val();
    let unitPrice = $("#txtItemUnitPrice").val();
    let qtyOnHand = $("#txtItemQtyOnHand").val();

    const itemObj = {
        code: code,
        description: description,
        unitPrice: unitPrice,
        qtyOnHand: qtyOnHand
    };

    $.ajax({
        url: baseUrl + "/saveItem",
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
    var searchItemCode = $("#txtItemCode").val();
    $.ajax({
        url: baseUrl + "/searchItem/" + searchItemCode,
        method: "GET",
        success: function (res) {
            $("#txtItemDescription").val(res.description);
            $("#txtItemUnitPrice").val(res.unitPrice);
            $("#txtItemQtyOnHand").val(res.qtyOnHand);
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

    const itemObj = {
        code: code,
        description: description,
        unitPrice: unitPrice,
        qtyOnHand: qtyOnHand
    };

    $.ajax({
        url: baseUrl + "/updateItem",
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
    var deletedItemCode = $("#txtItemCode").val();

    $.ajax({
        url: baseUrl + "/deleteItem/" + deletedItemCode,
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
        url: baseUrl + "/loadAllItems",
        method: "GET",
        success: function (res) {
            res.forEach(customer => {
                let code = customer.code;
                let description = customer.description;
                let unitPrice = customer.unitPrice;
                let qtyOnHand = customer.qtyOnHand;

                let row = `<tr>
                    <td>${code}</td>
                    <td>${description}</td>
                    <td>${unitPrice}</td>
                    <td>${qtyOnHand}</td>
                </tr>`;
                $("#itemTable").append(row);
            });
            tableListener();
            clearInputs();
        },
        error: function (error) {
            console.log("Load All Customers Error...!");
        }
    });
}

function tableListener() {
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

function clearInputs() {
    $("#txtItemCode").val("");
    $("#txtItemDescription").val("");
    $("#txtItemUnitPrice").val("");
    $("#txtItemQtyOnHand").val("");
}