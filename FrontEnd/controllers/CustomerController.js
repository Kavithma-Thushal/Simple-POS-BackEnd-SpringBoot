let customerUrl = "http://localhost:8080/api/v1/customer";

loadAllCustomers();

$("#btnSaveCustomer").click(function () {
    let id = $("#txtCustomerId").val();
    let name = $("#txtCustomerName").val();
    let address = $("#txtCustomerAddress").val();
    let salary = $("#txtCustomerSalary").val();

    let customerObj = {
        id: id,
        name: name,
        address: address,
        salary: salary
    };

    $.ajax({
        url: customerUrl + "/saveCustomer",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(customerObj),
        success: function (res) {
            loadAllCustomers();
            alert("Customer Saved Successfully...!");
        }, error: function (error) {
            alert("Customer Saved Error...!");
        }
    });
});

$("#btnSearchCustomer").click(function () {
    let searchId = $("#txtCustomerId").val();
    $.ajax({
        url: customerUrl + "/searchCustomer/" + searchId,
        method: "GET",
        success: function (res) {
            $("#customerTable").empty();
            let row = "<tr><td>" + res.id + "</td><td>" + res.name + "</td><td>" + res.address + "</td><td>" + res.salary + "</td></tr>";
            $("#customerTable").append(row);
            console.log("Customer Searched Successfully...!");
        },
        error: function (error) {
            console.log("Customer Searched Error...!");
        }
    });
});

$("#btnUpdateCustomer").click(function () {
    let id = $("#txtCustomerId").val();
    let name = $("#txtCustomerName").val();
    let address = $("#txtCustomerAddress").val();
    let salary = $("#txtCustomerSalary").val();

    let customerObj = {
        id: id,
        name: name,
        address: address,
        salary: salary
    };

    $.ajax({
        url: customerUrl + "/updateCustomer",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(customerObj),
        success: function (res) {
            loadAllCustomers();
            alert("Customer Updated Successfully...!");
        },
        error: function (error) {
            alert("Customer Updated Error...!");
        }
    });
});

$("#btnDeleteCustomer").click(function () {
    let deleteId = $("#txtCustomerId").val();

    $.ajax({
        url: customerUrl + "/deleteCustomer/" + deleteId,
        method: "DELETE",
        success: function (res) {
            loadAllCustomers();
            alert("Customer Deleted Successfully...!");
        },
        error: function (error) {
            alert("Customer Deleted Error...!");
        }
    });
});

$('#btnLoadAllCustomers').click(function () {
    loadAllCustomers();
});

function loadAllCustomers() {
    $('#customerTable').empty();

    $.ajax({
        url: customerUrl + "/loadAllCustomers",
        method: "GET",
        success: function (res) {
            res.forEach(customer => {
                let id = customer.id;
                let name = customer.name;
                let address = customer.address;
                let salary = customer.salary;

                let row = `<tr>
                    <td>${id}</td>
                    <td>${name}</td>
                    <td>${address}</td>
                    <td>${salary}</td>
                </tr>`;
                $("#customerTable").append(row);
            });
            customerTableListener();
            clearCustomerInputs();
        },
        error: function (error) {
            console.log("Load All Customers Error...!");
        }
    });
}

function customerTableListener() {
    $("#customerTable>tr").on("click", function () {
        let id = $(this).children().eq(0).text();
        let name = $(this).children().eq(1).text();
        let address = $(this).children().eq(2).text();
        let salary = $(this).children().eq(3).text();

        $("#txtCustomerId").val(id);
        $("#txtCustomerName").val(name);
        $("#txtCustomerAddress").val(address);
        $("#txtCustomerSalary").val(salary);
    });
}

function clearCustomerInputs() {
    $("#txtCustomerId").val("");
    $("#txtCustomerName").val("");
    $("#txtCustomerAddress").val("");
    $("#txtCustomerSalary").val("");
}