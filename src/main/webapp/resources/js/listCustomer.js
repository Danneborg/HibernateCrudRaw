$(document).ready(function () {
    $('a.deleteLink').on('click', function (e) {
        if(!confirm("Are you sure  you want to delete this Customer?")){
            return false;
        }
    })
});