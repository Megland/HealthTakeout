
$.extend( $.fn.dataTable.defaults, {
    searching: false,
    ordering:  true,
    lengthChange:false,
    "language": {
        "emptyTable":     "无数据",
        "info":           "当前 _START_ 到 _END_ 条(共 _TOTAL_ 条)",
        "infoEmpty":      "",
        "infoFiltered":   "(filtered from _MAX_ total entries)",
        "infoPostFix":    "",
        "thousands":      ",",
        "lengthMenu":     "Show _MENU_ entries",
        "loadingRecords": "加载中...",
        "processing":     "加载中...",
        "search":         "Search:",
        "zeroRecords":    "未找到匹配的记录！",
        "paginate": {
            "first":      "首页",
            "last":       "最后",
            "next":       "下页",
            "previous":   "上页"
        },
        "aria": {
            "sortAscending":  ": activate to sort column ascending",
            "sortDescending": ": activate to sort column descending"
        }
    },
    "columnDefs": [{
  		"targets": "_all",
  		"defaultContent": ""
	}],
	"aoColumnDefs": [
	       			{
	    				sDefaultContent: '',
	    				aTargets: [ '_all' ]
	    			}
	    		]
} );

$.fn.dataTable.ext.errMode='none';









