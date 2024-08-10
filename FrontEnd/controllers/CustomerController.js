let baseUrl = "http://localhost:8080/api/v1/customer";

loadAllCustomers();

$("#btnSaveCustomer").click(function () {
    let id = $("#txtCustomerId").val();
    let name = $("#txtCustomerName").val();
    let address = $("#txtCustomerAddress").val();
    let salary = $("#txtCustomerSalary").val();

    const customerObj = {
        id: id,
        name: name,
        address: address,
        salary: salary
    };

    $.ajax({
        url: baseUrl + "/saveCustomer",
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
    var searchCusId = $("#txtCustomerId").val();
    $.ajax({
        url: baseUrl + "/searchCustomer/" + searchCusId,
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

    const customerObj = {
        id: id,
        name: name,
        address: address,
        salary: salary
    };

    $.ajax({
        url: baseUrl + "/updateCustomer",
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
    var deletedCusId = $("#txtCustomerId").val();

    $.ajax({
        url: baseUrl + "/deleteCustomer/" + deletedCusId,
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
        url: baseUrl + "/loadAllCustomers",
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
            tableListener();
            clearInputs();
        },
        error: function (error) {
            console.log("Load All Customers Error...!");
        }
    });
}

function tableListener() {
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

function clearInputs() {
    $("#txtCustomerId").val("");
    $("#txtCustomerName").val("");
    $("#txtCustomerAddress").val("");
    $("#txtCustomerSalary").val("");
}