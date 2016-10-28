var TableDatatables = function (table) {
    var e = function (table) {


        var a = table, l = a.dataTable({
            lengthMenu: [[5, 15, 20, -1], [5, 15, 20, "All"]],
            pageLength: 5,
            language: {lengthMenu: " _MENU_ records"},
            columnDefs: [{orderable: !0, targets: [0]}, {searchable: !0, targets: [0]}],
            order: [[0, "dsc"]]
        });

    };

    return {
        init: function (table) {
            e(table)
        }
    }
}();
