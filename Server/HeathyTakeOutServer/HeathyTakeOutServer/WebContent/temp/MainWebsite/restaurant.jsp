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

<title>商铺管理</title>
</head>


<body class="hold-transition skin-green layout-top-nav">
	<div class="wrapper">
		<div class="main-header">
			<div class="navbar navbar-static-top">
				<div class="navbar-header">
					<a href="#" class="navbar-brand">商铺管理</a>
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
						<li class="active"><a href="restaurant.jsp" id="restaurantshow">商铺管理 <span 
						class="sr-only">(current)</span></a></li>
						<li class="dropdown"><a href="feedback.jsp">用户反馈 <span 
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
					<h4 class="box-title">商铺信息</h4>
				</div>
				<div class="box-body">
					<div class="box-header with-border">
						<button type="button" name="add"
							class="btn btn-info pull-left addBtn">
							<i class="fa fa-plus-circle"></i>添加
						</button>
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
								id="keyword" placeholder="商铺名称" />
						</div>
					</div>
					<table width="100%" id="dataTable" class="table" >
						<thead>
							<tr>
								<th width="5%">商铺号</th>
								<th width="15%">商铺名称</th>
								<th width="30%">简介</th>
								<th width="30%">地址</th>
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
		<!-- 信息弹窗 -->
	<div class="modal" id="restaurantModal">
		<div class="modal-dialog" role="dialog">
			<div class="modal-content">
				<form id="userEditForm">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">
							<strong>商铺信息</strong>
						</h4>
					</div>
						<div class="modal-body">
							
							<div class="form-horizontal">
								<div class="row">
					
									<input type="hidden" name="restaurantid" id="restaurantid" />
									
									<div class="form-group">
										<label class="col-sm-2 control-label">商铺名称</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="restaurantname"
													id="restaurantname" style="width:460px"/>
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-2 control-label">简介</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="introduction"
													id="introduction" style="width:460px"/>
											</div>
										</div>
									</div>
										
									
									<div class="form-group">
										<label class="col-sm-2 control-label">地址</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="restaurantaddress"
													id="restaurantaddress" style="width:460px"/>
											</div>
										</div>
									</div>
	
								</div>
								
							</div>
						</div>
					<div class="modal-footer">
							<button type="button" class="btn btn-primary saveBtn">保存</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">返回</button>
					</div>
				</form>
			</div>

		</div>
	</div>
	
	
	<div class="modal" id="QueryfoodbyrestaurantModal">
		<div class="modal-dialog" role="dialog" style="width:1200px">
			<div class="modal-content">
				<form id="userEditForm">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">
							<strong>该商户在售商品信息</strong>
						</h4>
					</div>
					
					<div class="modal-body">
						<table  id="foodTable" class="table" >
							<thead>
								<tr>
									<th width="5%">商品号</th>
									<th width="5%">商品类别</th>
									<th width="10%">商品名称</th>
									<th width="5%">价格</th>
									<th width="5%">卡路里</th>
									<th width="10%">供应商</th>
									<th width="40%">详细信息</th>
								</tr>
							</thead>
							<tbody id="stuInfoList">
							</tbody>
						</table>
					</div>
					
					<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">返回</button>
					</div>
				</form>
			</div>

		</div>
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
			findRestaurant("");
		})
		
		//打开增加信息的弹窗
		$('.addBtn').on('click',function(){
			
			jQuery("#restaurantid").val('');
			jQuery("#restaurantname").val('');
			jQuery("#introduction").val('');
			jQuery("#restaurantaddress").val('');
			
			jQuery("#restaurantModal").modal('show');
		});
	    
		//保存信息
		$('.saveBtn').on('click',function(){
			saveRestaurant();
		});
		
		//查询信息
	    $('.searchBtn').on('click',function(){
	    	var key=$('#keyword').val();
	    	findRestaurant(key);	
	    })
	    
	    //刷新表格
	    $('.resetBtn').on('click',function(){
	    	findRestaurant("");	
	    })
	    
	    ///保存信息
		function saveRestaurant(){
			var url='<%=request.getContextPath()%>/RestaurantInsertServlet';
			
			var restaurantid = $('#restaurantid').val();
			var restaurantname = $('#restaurantname').val();
			var introduction = $('#introduction').val();
			var restaurantaddress = $('#restaurantaddress').val();

			
			///传到后台保存的JSON数据
			var data = {
				'restaurantid' : restaurantid,
				'restaurantname' : restaurantname,
				'introduction' : introduction,
				'restaurantaddress' : restaurantaddress,
			}
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				data:data,
				success : function(data) {
					if (data.code == 1) {
						findRestaurant('');
						jQuery("#restaurantModal").modal('hide');
					} else {
						alert('保存失败')
					}
				}
			})
		}
		
		
		//查找信息
		function findRestaurant(key){
			var url='<%=request.getContextPath()%>/RestaurantQuery?key='+key;
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

                    	for(var i=0;i<data.restaurant.length;i++){
                    		var row=document.createElement('tr');
                    		row.innerHTML="<td>"+data.restaurant[i].restaurantid+"</td>"+"<td>"
                    		+ data.restaurant[i].restaurantname
							+ "</td>"
							+ "<td>"
							+ data.restaurant[i].introduction
							+ "</td>"
							+ "<td>"
							+ data.restaurant[i].restaurantaddress
							+ "</td>"
                    		+"<td><button class='btn btn-sm btn-default delBtn' onclick='deleteRestaurant(this)' value='"+data.restaurant[i].restaurantid+"'>删除</button>"
                    		+"<button class='btn btn-sm btn-default delBtn' onclick='findfoodbyRestaurantid(this)' value='"+data.restaurant[i].restaurantid+"'>查看商品</button>"
                    		+"<button class='btn btn-sm btn-default editBtn' onclick='editRestaurant(this)' value='"+(i+1)+"'>修改</button></td>";
                    		t1.appendChild(row);
                    	}
                    	
					} 
                }
		   })
		}
		
		//查看findfoodbyRestaurantid
		function findfoodbyRestaurantid(d){
			jQuery("#QueryfoodbyrestaurantModal").modal('show');
			var restaurantid=d.value;
			var url='<%=request.getContextPath()%>/FoodQuerybyrestaurantID?key='+restaurantid;
			var t1=document.getElementById("foodTable");
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

                    	for(var i=0;i<data.food.length;i++){
                    		var row=document.createElement('tr');
                    		row.innerHTML="<td>"+data.food[i].foodno+"</td>"+"<td>"
                    		+ data.food[i].category
							+ "</td>"
							+ "<td>"
							+ data.food[i].foodname
							+ "</td>"
							+ "<td>"
							+ data.food[i].price
							+ "</td>"
							+ "<td>"
							+ data.food[i].calories
							+ "</td>"
							+ "<td>"
							+ data.food[i].supplier
							+ "</td>"
							+ "<td>"
							+ data.food[i].information
							+ "</td>"
                    		t1.appendChild(row);
                    	}
                    	
					} 
                }
		   })
		}
		

		
		
		//编辑信息
		function editRestaurant(d) {
			jQuery("#restaurantModal").modal('show');
			var i = d.value;
			var table = document.getElementById("dataTable");
			var rows = table.rows;
			
			var restaurantid = rows[i].cells[0].innerHTML;
			var restaurantname = rows[i].cells[1].innerHTML;
			var introduction = rows[i].cells[2].innerHTML;
			var restaurantaddress=rows[i].cells[3].innerHTML;

			
			$('#restaurantid').val(restaurantid);
			$('#restaurantname').val(restaurantname);
			$('#introduction').val(introduction);
			$('#restaurantaddress').val(restaurantaddress);
		}
		
		
		
		//删除学生信息
		function deleteRestaurant(d){
			if(!confirm("是否注销该商铺")) return;
			var restaurantid=d.value
			var url='<%=request.getContextPath()%>/RestaurantDelServlet?restaurantid='+ restaurantid;
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