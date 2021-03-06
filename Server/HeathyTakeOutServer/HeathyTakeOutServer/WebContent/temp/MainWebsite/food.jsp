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

<title>外卖商品管理</title>
</head>


<body class="hold-transition skin-green layout-top-nav">
	<div class="wrapper">
		<div class="main-header">
			<div class="navbar navbar-static-top">
				<div class="navbar-header">
					<a href="#" class="navbar-brand">外卖商品管理</a>
				</div>
				<!--顶部导航栏菜单按钮-->
				<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="food.jsp" id="foodshow">外卖商品 <span
								class="sr-only">(current)</span></a></li>
						<li class="dropdown"><a href="order.jsp">订单管理 <span 
								class="sr-only">(current)</span></a></li>
						<li class="dropdown"><a href="user.jsp">用户管理 <span 
						class="sr-only">(current)</span></a></li>
						
						<li class="dropdown"><a href="restaurant.jsp">商铺管理 <span 
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
					<h4 class="box-title">商品详情</h4>
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
								id="keyword" placeholder="商品名称" />
						</div>
					</div>
					<table width="100%" id="dataTable" class="table" >
						<thead>
							<tr>
								<th width="5%">商品号</th>
								<th width="5%">商品类别</th>
								<th width="10%">商品名称</th>
								<th width="5%">价格</th>
								<th width="5%">卡路里</th>
								<th width="10%">供应商</th>
								<th width="40%">详细信息</th>
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
		<!-- 学生信息弹窗 -->
	<div class="modal" id="foodModal">
		<div class="modal-dialog" role="dialog">
			<div class="modal-content">
				<form id="userEditForm">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">
							<strong>商品信息</strong>
						</h4>
					</div>
						<div class="modal-body">
							
							<div class="form-horizontal">
								<div class="row">
									<div class="form-group">
										<input type="hidden" name="foodno" id="foodno" />
										<label class="col-sm-2 control-label">商品类别</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<select style="width: 100%" class="form-control"
													name="category" id="category">
													<option value="主食类">主食类</option>
													<option value="菜品类">菜品类</option>
													<option value="饮品类">饮品类</option>
												</select>
											</div>
											<div class="col-md-2">
												<span style="color: #F00; fontsize: 30px">*</span>
											</div>
										</div>
										
										<label class="col-sm-2 control-label">商品名称</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="foodname"
													id="foodname" />
											</div>
											<div class="col-md-2">
												<span style="color: #F00; fontsize: 30px">*</span>
											</div>
										</div>
										
									</div>
									
									<div class="form-group">
										<label class="col-sm-2 control-label">价格</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="price"
													id="price" />
											</div>
											<div class="col-md-2">
												<span style="color: #F00; fontsize: 30px">*</span>
											</div>
										</div>
										
										<label class="col-sm-2 control-label">卡路里</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="calories"
													id="calories"/>
											</div>
											<div class="col-md-2">
												<span style="color: #F00; fontsize: 30px">*</span>
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-2 control-label">供应商</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="supplier"
													id="supplier" style="width:300px" />
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-2 control-label">详细信息</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="information"
													id="information" style="width:460px"/>
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
	</div>
	

	<!-- jQuery 2.1.4 -->
	<script
		src="<%=request.getContextPath()%>/resource/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script
		src="<%=request.getContextPath()%>/resource/bootstrap/js/bootstrap.min.js"></script>
		
	<script type="text/javascript">
	

		jQuery(document).ready(function() {
			findFood("");
		})
		

		$('.addBtn').on('click',function(){
			
			jQuery("#foodno").val('');
			jQuery("#category").val('');
			jQuery("#foodname").val('');
			jQuery("#price").val('');
			jQuery("#calories").val('');
			jQuery("#supplier").val('');
			jQuery("#information").val('');
			
			jQuery("#foodModal").modal('show');
		});
	    

		$('.saveBtn').on('click',function(){
			saveFood();
		});

	    $('.searchBtn').on('click',function(){
	    	var key=$('#keyword').val();
	    	findFood(key);	
	    })
	    

	    $('.resetBtn').on('click',function(){
	    	findFood("");	
	    })
	    
		function saveFood(){
			var url='<%=request.getContextPath()%>/FoodInsertServlet';
			
			var foodno = $('#foodno').val();
			var category = $('#category').val();
			var foodname = $('#foodname').val();
			var price = $('#price').val();
			var calories = $('#calories').val();
			var supplier = $('#supplier').val();
			var information = $('#information').val();
			
			///传到后台保存的JSON数据
			var data = {
				'foodno' : foodno,
				'category' : category,
				'foodname' : foodname,
				'price' : price,
				'calories' : calories,
				'supplier' : supplier,
				'information' : information
			}
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				data:data,
				success : function(data) {
					if (data.code == 1) {
						findFood('');
						jQuery("#foodModal").modal('hide');
					} else {
						alert('保存失败')
					}
				}
			})
		}
		
		
		function findFood(key){
			var url='<%=request.getContextPath()%>/FoodQuery?key='+key;
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
                    		+"<td><button class='btn btn-sm btn-default delBtn' onclick='deleteFood(this)' value='"+data.food[i].foodno+"'>删除</button>"
                    		+"<button class='btn btn-sm btn-default editBtn' onclick='editFood(this)' value='"+(i+1)+"'>修改</button></td>";
                    		t1.appendChild(row);
                    	}
                    	
					} 
                }
		   })
		}
		
		
		function editFood(d) {
			jQuery("#foodModal").modal('show');
			var i = d.value;
			var table = document.getElementById("dataTable");
			var rows = table.rows;
			var foodno = rows[i].cells[0].innerHTML;
			var category = rows[i].cells[1].innerHTML;
			var foodname = rows[i].cells[2].innerHTML;
			var price=rows[i].cells[3].innerHTML;
			var calories=rows[i].cells[4].innerHTML;
			var supplier = rows[i].cells[5].innerHTML;
			var information = rows[i].cells[6].innerHTML;
			
			$('#foodno').val(foodno);
			$('#foodname').val(foodname);
			$('#price').val(price);
			$('#calories').val(calories);
			$('#supplier').val(supplier);		
			$('#information').val(information);
		}
		
		function deleteFood(d){
			if(!confirm("是否删除该商品")) return;
			var foodno=d.value
			var url='<%=request.getContextPath()%>/FoodDelServlet?foodno='+ foodno;
			$.ajax({
				type : "get",
				url : url,
				dataType : "json",
				success : function(data) {
					if (data.code == 1) {
						var row = d.parentNode.parentNode;//得到当前元素父节点的父节点(tr)
						row.parentNode.removeChild(row);//删除当前行
					} else {
						alert("该商品当前存在订单不能删除！")
					}
				}
			})
		}
	</script>
</body>

</html>