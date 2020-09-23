<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/dist/css/AdminLTE.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/dist/css/skins/_all-skins.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/font-awesome-4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/datatables/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resource/mycss/mystlye.css">
	
<style type="text/css">
table {
      text-align:center; /*设置水平居中*/
      vertical-align:middle;/*设置垂直居中*/
      font-size: 12px;
}
th {
      text-align:center; /*设置水平居中*/
      vertical-align:middle;/*设置垂直居中*/
      font-size: 14px;
}
</style>

<title>用户反馈</title>
</head>


<body class="hold-transition skin-green layout-top-nav">
	<div class="wrapper">
		<div class="main-header">
			<div class="navbar navbar-static-top">
				<div class="navbar-header">
					<a href="#" class="navbar-brand">用户反馈</a>
				</div>
				<!--顶部导航栏菜单按钮-->
				<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="food.jsp" id="foodshow">外卖商品 <span
								class="sr-only">(current)</span></a></li>
						<li class="dropdown"><a href="order.jsp">订单管理 <span 
								class="sr-only">(current)</span></a></li>
						<li class="dropdown"><a href="user.jsp">用户管理 <span 
						class="sr-only">(current)</span></a></li>
						
						<li class="dropdown"><a href="restaurant.jsp">商铺管理 <span 
						class="sr-only">(current)</span></a></li>
						
						<li class="active"><a href="feedback.jsp" id="restaurantshow">用户反馈 <span 
						class="sr-only">(current)</span></a></li>
						
					</ul>


				</div>
				<div class="collapse navbar-collapse pull-right" id="navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="<%=request.getContextPath()%>/temp/login/login.jsp">退出登录 <span 
								class="sr-only">(current)</span></a></li>
					</ul>

				</div>
				<!-- /.navbar-collapse -->
				<!--顶部导航栏右侧的-->
			</div>
		</div>
		<div class="content-wrapper">
			<div class="content" style="min-height: 560px">
				<div class="box-header with-border">
					<i class="fa fa-th-list"></i>
					<h4 class="box-title">用户反馈</h4>
				</div>
				<div class="box-body">
					<div class="box-header with-border">
						<button type="button" name="cancel" id="cancel-btn"
							class="btn btn-info pull-left resetBtn" title="刷新">
							<i class="fa fa-refresh"></i>刷新
						</button>
						<div class="form-inline pull-right">
							<button type="button" name="search" id="search-btn"
								class="btn btn-default pull-right searchBtn" title="查询">
								<i class="fa fa-search"></i>
							</button>
							<input type="text"
								class="form-control pull-right name-search position-search"
								id="keyword" placeholder="搜索" />
						</div>
					</div>
					<table width="100%" id="dataTable" class="table" >
						<thead>
							<tr>
								<th width="5%">反馈序号</th>
								<th width="15%">用户</th>
								<th width="30%">反馈内容</th>
								<th width="30%">用户联系方式</th>
								<th width="20%">操作</th>
							</tr>
						</thead>
						<tbody id="stuInfoList">
						</tbody>
					</table>
				</div>
			</div>

		</div>
		<div class="main-footer">
			<div class="pull-right hidden-xs">
				<strong>Copyright &copy; 2020 <a href="#">Jing Yinluo</a></strong>
			</div>
			<b>Version</b> 1.0.0

		</div>

	
	<!-- jQuery 2.1.4 -->
	<script
		src="<%=request.getContextPath()%>/resource/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script
		src="<%=request.getContextPath()%>/resource/bootstrap/js/bootstrap.min.js"></script>
		
	<script type="text/javascript">
	
	    //加载页面自动获得所有信息
		jQuery(document).ready(function() {
			findFeedback("");
		})
		
		
		//查询信息
	    $('.searchBtn').on('click',function(){
	    	var key=$('#keyword').val();
	    	findFeedback(key);	
	    })
	    
	    //刷新表格
	    $('.resetBtn').on('click',function(){
	    	findFeedback("");	
	    })
	    
		
		//查找信息
		function findFeedback(key){
			var url='<%=request.getContextPath()%>/FeedbackQuery?key='+key;
			var t1=document.getElementById("dataTable");
			$.ajax({
				type : "get",
				url : url,
				dataType : "json",
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				success : function(data) {//根据返回的数据画出表格
                    if(data!=null){
                    	/*加载表格前先删除除第一行的所有行 */
			            var rows=t1.rows;
                    	for(var i=rows.length-1;i>0;i--){
                    		rows[i].remove();
                    	}

                    	for(var i=0;i<data.feedback.length;i++){
                    		var row=document.createElement('tr');
                    		row.innerHTML="<td>"+data.feedback[i].feedbackno+"</td>"+"<td>"
                    		+ data.feedback[i].username
							+ "</td>"
							+ "<td>"
							+ data.feedback[i].feedbackdetail
							+ "</td>"
							+ "<td>"
							+ data.feedback[i].contactdetail
							+ "</td>"
                    		+"<td><button class='btn btn-sm btn-default delBtn' onclick='deleteFeedback(this)' value='"+data.feedback[i].feedbackno+"'>删除</button>";
                    		t1.appendChild(row);
                    	}
                    	
					} 
                }
		   })
		}
		
		//删除学生信息
		function deleteFeedback(d){
			if(!confirm("是否删除该反馈")) return;
			var feedbackno=d.value
			var url='<%=request.getContextPath()%>/FeedbackDelServlet?feedbackno='+ feedbackno;
			$.ajax({
				type : "get",
				url : url,
				dataType : "json",
				success : function(data) {
					if (data.code == 1) {
						var row = d.parentNode.parentNode;//得到当前元素父节点的父节点(tr)
						row.parentNode.removeChild(row);//删除当前行
					} else {
						alert("注销失败！")
					}
				}
			})
		}
	</script>
</body>

</html>