$(function () {
    updateTable();
});

function updateTable() {
    $.ajax({
        type: "get",
        url: "/todo/getitems",
        data: {
            done: $("#done")[0].checked
        }
    }).done(function (items) {
        $('#table tbody').html("<tr></tr>");
        $.each(items, function (i, item) {
            var row = "<tr><td>" + item.id + "</td><td>" + item.description + "</td><td>" + new Date(item.created).toLocaleString("ru", getDateOptions()) + "</td>";
            row += item.done ?
                "<td><div class='form-check form-switch'><input class='form-check-input' onclick='checkDone(this)' type='checkbox' id='" + item.id + "' checked></div></td>" :
                "<td><div class='form-check form-switch'><input class='form-check-input' onclick='checkDone(this)' type='checkbox' id='" + item.id + "'></div></td>";
            row += "</tr>";
            $('#table tbody tr:last').after(row);
        })
    })
}

$("#addbtn").click(function () {
    addTask();
});

function checkDone(item) {
    $.ajax({
        type: "post",
        url: "/todo/additem",
        data: {
            id: $(item)[0].id,
            done: $(item)[0].checked
        }
    }).done(function () {
        updateTable();
    }).fail(function () {
        alert("Не удалось добавить задание.");
    });
}

function validate() {
    if ($('#desc').val() === '') {
        $('#alert').html(alertBody("Для добавления задания, необходимо написать задание."));
        return false;
    }
    return true;
}

function addTask() {
    if (validate()) {
        $.ajax({
            type: "post",
            url: "/todo/additem",
            data: {
                id: 0,
                desc: $('#desc').val(),
                created: null,
                done: false
            }
        }).done(function () {
            updateTable();
        }).fail(function () {
            alert("Не удалось добавить задание.");
        });
    }
}

function getDateOptions(){
    return {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        timezone: 'UTC',
        hour: 'numeric',
        minute: 'numeric'
    }
}

function alertBody(message) {
    return '<div class="alert alert-primary alert-dismissible fade show" role="alert">' + message + '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button> </div>';
}