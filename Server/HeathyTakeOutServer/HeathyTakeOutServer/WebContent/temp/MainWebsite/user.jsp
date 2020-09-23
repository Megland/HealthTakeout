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

<title>用户管理</title>
</head>


<body class="hold-transition skin-green layout-top-nav">
	<div class="wrapper">
		<div class="main-header">
			<div class="navbar navbar-static-top">
				<div class="navbar-header">
					<a href="#" class="navbar-brand">用户管理</a>
				</div>
				<!--顶部导航栏菜单按钮-->
				<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="food.jsp" id="food.jsp">外卖商品 <span
								class="sr-only">(current)</span></a></li>
						<li class="dropdown"><a href="order.jsp">订单管理 <span 
								class="sr-only">(current)</span></a></li>
								
						<li class="active"><a href="user.jsp">用户管理 <span 
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
					<h4 class="box-title">用户信息</h4>
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
								id="keyword" placeholder="用户名称" />
						</div>
					</div>
					<table width="100%" id="dataTable" class="table" >
						<thead>
							<tr>
								<th width="5%">序号</th>
								<th width="20%">用户名</th>
								<th width="5%">性别</th>
								<th width="5%">年龄</th>
								<th width="5%">身高</th>
								<th width="10%">体重</th>
								<th width="10%">目标体重</th>
								<th width="20%">偏好</th>
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
		<!-- 用户信息弹窗 -->
	<div class="modal" id="userModal">
		<div class="modal-dialog" role="dialog">
			<div class="modal-content">
				<form id="userEditForm">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">
							<strong>用户信息</strong>
						</h4>
					</div>
						<div class="modal-body">
							
							<div class="form-horizontal">
								<div class="row">
									<div class="form-group">
									
										<input type="hidden" name="userno" id="userno" />
										
										<label class="col-sm-2 control-label">用户名</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="username"
													id="username" />
											</div>
											<div class="col-md-2">
												<span style="color: #F00; fontsize: 30px">*</span>
											</div>
										</div>

										
										<label class="col-sm-2 control-label">性别</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="gender"
													id="gender" />
											</div>
											<div class="col-md-2">
												<span style="color: #F00; fontsize: 30px">*</span>
											</div>
										</div>
										
									</div>
									
									<div class="form-group">
										<label class="col-sm-2 control-label">年龄</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="age" 
													id="age" />
											</div>
											<div class="col-md-2">
												<span style="color: #F00; fontsize: 30px">*</span>
											</div>
										</div>
										
										<label class="col-sm-2 control-label">身高</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="height" 
													id="height"/>
											</div>
											<div class="col-md-2">
												<span style="color: #F00; fontsize: 30px">*</span>
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-2 control-label">体重</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="weight" 
													id="weight"  />
											</div>
										</div>
										
										<label class="col-sm-2 control-label">目标体重</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="targetweight" 
													id="targetweight" />
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-2 control-label">偏好</label>
										<div class="col-sm-4">
											<div class="col-md-10">
												<input class="form-control" type="text" name="preference" 
													id="preference"/>
											</div>
										</div>
									</div>
	
								</div>
								<span style="color: #F00; fontsize: 30px">初始密码将自动设置为123456，用户其他信息由用户登录后自行设定</span>
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
	<!-- 学生信息弹窗 -->
	</div>
	

	<!-- jQuery 2.1.4 -->
	<script
		src="<%=request.getContextPath()%>/resource/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- Bootstrap 3.3.5 -->
	<script
		src="<%=request.getContextPath()%>/resource/bootstrap/js/bootstrap.min.js"></script>
		
	<script type="text/javascript">
	
	    //加载页面自动获得所有学生信息
		jQuery(document).ready(function() {
			findUser("");
		})
		
		//打开增加学生信息的弹窗
		$('.addBtn').on('click',function(){
			
			jQuery("#userno").val('');
			jQuery("#username").val('');
			jQuery("#password").val('');
			jQuery("#gender").val('');
			jQuery("#age").val('0');
			jQuery("#height").val('0');
			jQuery("#weight").val('0');
			jQuery("#targetweight").val('0');
			jQuery("#preference").val('');
			jQuery("#userModal").modal('show');
		});
	    
		
		$('.saveBtn').on('click',function(){
			saveUser();
		});
		

	    $('.searchBtn').on('click',function(){
	    	var key=$('#keyword').val();
	    	findUser(key);	
	    })
	    
	    //刷新表格
	    $('.resetBtn').on('click',function(){
	    	findUser("");	
	    })
	    
	
		function saveUser(){
			var url='<%=request.getContextPath()%>/UserInsertServlet';
			
			var userno = $('#userno').val();
			var username = $('#username').val();
			var password = $('#password').val();
			var gender = $('#gender').val();
			var age = $('#age').val();
			var height = $('#height').val();
			var weight = $('#weight').val();
			var targetweight = $('#targetweight').val();
			var preference = $('#preference').val();
			
			///传到后台保存的JSON数据
			var data = {
				'userno' : userno,
				'username' : username,
				'password' : password,
				'gender' : gender,
				'age' : age,
				'height' : height,
				'weight' : weight,
				'targetweight' : targetweight,
				'preference' : preference
			}
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				data:data,
				success : function(data) {
					if (data.code == 1) {
						findUser('');
						jQuery("#userModal").modal('hide');
					} else {
						alert('保存失败')
					}
				}
			})
		}
		
		

		function findUser(key){
			var url='<%=request.getContextPath()%>/UserQuery?key='+key;
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

                    	for(var i=0;i<data.user.length;i++){
                    		var row=document.createElement('tr');
                    		row.innerHTML="<td>"+data.user[i].userno+"</td>"+"<td>"
                    		+ data.user[i].username
							+ "</td>"
							+ "<td>"
							+ data.user[i].gender
							+ "</td>"
							+ "<td>"
							+ data.user[i].age
							+ "</td>"
							+ "<td>"
							+ data.user[i].height
							+ "</td>"
							+ "<td>"
							+ data.user[i].weight
							+ "</td>"
							+ "<td>"
							+ data.user[i].targetweight
							+ "</td>"
							+ "<td>"
							+ data.user[i].preference
							+ "</td>"
                    		+"<td><button class='btn btn-sm btn-default delBtn' onclick='deleteUser(this)' value='"+data.user[i].userno+"'>销户</button>"
                    		+"<button class='btn btn-sm btn-default delBtn' onclick='resetUserPassword(this)' value='"+(i+1)+"'>重置密码</button>"
                    		+"<button class='btn btn-sm btn-default editBtn' onclick='editUser(this)' value='"+(i+1)+"'>修改</button></td>";
                    		t1.appendChild(row);
                    	}
                    	
					} 
                }
		   })
		}
		
		
		
		function resetUserPassword(d){
			
			if(!confirm("是否重置此用户密码为123456")) return;
			var url='<%=request.getContextPath()%>/UserInsertServlet';
			
			var i = d.value;
			var table = document.getElementById("dataTable");
			var rows = table.rows;
			
			var userno = rows[i].cells[0].innerHTML;
			var username = rows[i].cells[1].innerHTML;
			var password = "123456";
			var gender = rows[i].cells[2].innerHTML;
			var age=rows[i].cells[3].innerHTML;
			var height=rows[i].cells[4].innerHTML;
			var weight = rows[i].cells[5].innerHTML;
			var targetweight = rows[i].cells[6].innerHTML;
			var preference = rows[i].cells[7].innerHTML;
			
			///传到后台保存的JSON数据
			var data = {
				'userno' : userno,
				'username' : username,
				'password' : password,
				'gender' : gender,
				'age' : age,
				'height' : height,
				'weight' : weight,
				'targetweight' : targetweight,
				'preference' : preference
			}
			$.ajax({
				type : "post",
				url : url,
				dataType : "json",
				data:data,
				success : function(data) {
					if (data.code == 1) {
						findUser('');
						confirm("此用户密码已重置为123456")
					} else {
						alert('失败')
					}
				}
			})
		}
		
		
		function editUser(d) {
			jQuery("#userModal").modal('show');
			var i = d.value;
			var table = document.getElementById("dataTable");
			var rows = table.rows;
			
			var userno = rows[i].cells[0].innerHTML;
			var username = rows[i].cells[1].innerHTML;
			var gender = rows[i].cells[2].innerHTML;
			var age=rows[i].cells[3].innerHTML;
			var height=rows[i].cells[4].innerHTML;
			var weight = rows[i].cells[5].innerHTML;
			var targetweight = rows[i].cells[6].innerHTML;
			var preference = rows[i].cells[7].innerHTML;
			
			$('#userno').val(userno);
			$('#username').val(username);
			$('#gender').val(gender);
			$('#age').val(age);
			$('#height').val(height);	
			$('#weight').val(weight);	
			$('#targetweight').val(targetweight);	
			$('#preference').val(preference);
		}
		
		
		
		function deleteUser(d){
			if(!confirm("是否永久注销（删除）此用户")) return;
			var userno=d.value
			var url='<%=request.getContextPath()%>/UserDelServlet?userno='+ userno;
			$.ajax({
				type : "get",
				url : url,
				dataType : "json",
				success : function(data) {
					if (data.code == 1) {
						var row = d.parentNode.parentNode;//得到当前元素父节点的父节点(tr)
						row.parentNode.removeChild(row);//删除当前行
					} else {
						alert("注销（删除）此用户失败")
					}
				}
			})
		}
	</script>
</body>

</html>