package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Food;
import entity.Message;
import entity.Order;
import entity.User;
import entity.Restaurant;
import entity.Shopcomment;
import entity.Trend;
import entity.Trendcomment;
import entity.Address;
import entity.Fans;
import entity.Feedback;
import entity.Focus;

public class DatabaseOperation {
	public static Connection getConn() {
		String driverName="com.mysql.jdbc.Driver";
		String dbURL="jdbc:mysql://localhost:3306/healthytakeout?useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Shanghai";
		String userName="root";
		String userPwd="root";
		try
		{
		    Class.forName(driverName);
		    Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
		    return dbConn;
		 }
		 catch(ClassNotFoundException e)
		 {
			  e.printStackTrace();
		 } 
		 catch(SQLException e) {
			  e.printStackTrace();
		 }
		 return null;
	}
	
	public boolean LoginfindMUser(String name,String password){	
    	Connection conn=getConn();
		String sql="select * from manager where musername=? and password=?";
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			ResultSet results=pst.executeQuery();
			while(results.next()) {
				return true;
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	return false;   	 
    }
	
	public int LoginUser(String name,String password){	
		List<User> user=new ArrayList<User>();
    	Connection conn=getConn();
		String sql="select * from user where username=? and password=?";
		PreparedStatement pst=null;
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			ResultSet results=pst.executeQuery();
			while(results.next()) {
				int userno=results.getInt("userno");
				return userno;
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	return 0;   	 
    }
	
	public List<Food> findFoodInfo(String key){
		List<Food> food=new ArrayList<Food>();
		Connection conn=getConn();
		String sql = "select * from food where foodno like '%"+key+"%' or foodname like '%"+key+"%'";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int foodno=results.getInt("foodno");
				String category=results.getString("category");
				String foodname=results.getString("foodname");
				float price=results.getFloat("price");
				float calories=results.getFloat("calories");
				String supplier=results.getString("supplier");
				String information=results.getString("information");
				Food s=new Food(foodno,category,foodname,price,calories,supplier,information);
				food.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return food;	
	}
		
	public List<Food> findFoodInfobyRestaurantid(String key){
		List<Food> food=new ArrayList<Food>();
		Connection conn=getConn();
		String sql = "SELECT foodno,category,foodname,price,calories,supplier,information from restaurant a,food b where a.restaurantid=b.restaurantid and a.restaurantid="+key;
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int foodno=results.getInt("foodno");
				String category=results.getString("category");
				String foodname=results.getString("foodname");
				float price=results.getFloat("price");
				float calories=results.getFloat("calories");
				String supplier=results.getString("supplier");
				String information=results.getString("information");
				Food s=new Food(foodno,category,foodname,price,calories,supplier,information);
				food.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return food;	
	}
	
	
	public int deleteFoodInfo(int no){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from food where foodno="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return i;
    }
	
	
	public int insertFoodInfo(Food s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into food(foodno,category,foodname,price,calories,supplier,information) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, s.getFoodno());
			pst.setString(2, s.getCategory());
			pst.setString(3, s.getFoodname());
			pst.setFloat(4, s.getPrice());
			pst.setFloat(5, s.getCalories());
			pst.setString(6, s.getSupplier());
			pst.setString(7, s.getInformation());
			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateFood(Food s){
		int i=0;
		Connection con=getConn();
		String sql="update food set category='"+s.getCategory()+"',foodname='"+s.getFoodname()+"',price="+s.getPrice()+",calories="+s.getCalories()+",supplier='"+s.getSupplier()+"',information='"+s.getInformation()+"'where"
				+ " foodno="+s.getFoodno();
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
		
	
	public List<User> findUserInfo(String key){
		List<User> user=new ArrayList<User>();
		Connection conn=getConn();
		String sql = "select * from user where userno like '%"+key+"%' or username like '%"+key+"%'";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int userno=results.getInt("userno");
				String username=results.getString("username");
				String password=results.getString("password");
				String tempgender=results.getString("gender");
				String gender="ÄÐ";
				if(tempgender.equals("1")) {
					gender="Å®";
				}
				int age=results.getInt("age");
				float height=results.getFloat("height");
				float weight=results.getFloat("weight");
				float targetweight=results.getFloat("targetweight");
				String preference=results.getString("preference");
				User s=new User(userno,username,password,gender,age,height,weight,preference,targetweight);
				user.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;	
	}
	
	public int deleteUserInfo(int no){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from user where userno="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return i;
    }
	
	
	public int insertUserInfo(User s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into user(userno,username,password,gender,age,height,weight,targetweight,preference) values(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, s.getUserno());
			pst.setString(2, s.getUsername());
			pst.setString(3, s.getPassword());
			pst.setString(4, s.getGender());
			pst.setInt(5, s.getAge());
			pst.setFloat(6, s.getHeight());
			pst.setFloat(7, s.getWeight());
			pst.setFloat(8, s.getTargetweight());
			pst.setString(9, s.getPreference());
			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateUser(User s){
		int i=0;
		Connection con=getConn();
		String sql="update user set username='"+s.getUsername()+"',password='"+s.getPassword()+"',gender='"+s.getGender()+"',age="+s.getAge()+",height="+s.getHeight()+",weight="+s.getWeight()+",targetweight="+s.getTargetweight()+",preference='"+s.getPreference()+"'where"
				+ " userno="+s.getUserno();
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;	
		
	}
	
	public List<Restaurant> findRestaurantInfo(String key){
		List<Restaurant> restaurant=new ArrayList<Restaurant>();
		Connection conn=getConn();
		String sql = "select * from restaurant where restaurantid like '%"+key+"%' or restaurantname like '%"+key+"%'";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int restaurantid=results.getInt("restaurantid");
				String restaurantname=results.getString("restaurantname");
				String introduction=results.getString("introduction");
				String restaurantaddress=results.getString("restaurantaddress");
				Restaurant s=new Restaurant(restaurantid,restaurantname,introduction,restaurantaddress);
				restaurant.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restaurant;	
	}
	
	public int deleteRestaurantInfo(int no){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from restaurant where restaurantid="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return i;
    }
	
	
	public int insertRestaurantInfo(Restaurant s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into restaurant(restaurantid,restaurantname,introduction,restaurantaddress) values(?,?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, s.getRestaurantid());
			pst.setString(2, s.getRestaurantname());
			pst.setString(3, s.getIntroduction());
			pst.setString(4, s.getRestaurantaddress());

			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateRestaurant(Restaurant s){
		int i=0;
		Connection con=getConn();
		String sql="update restaurant set restaurantname='"+s.getRestaurantname()+"',introduction='"+s.getIntroduction()+"',restaurantaddress='"+s.getRestaurantaddress()+"'where"
				+ " restaurantid="+s.getRestaurantid();
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;	
		
	}
	
	
	public List<Address> findAddressInfo(String key){
		List<Address> address=new ArrayList<Address>();
		Connection conn=getConn();
		String sql = "select * from address where addressno like '%"+key+"%' or userno like '%"+key+"%'";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int addressno=results.getInt("addressno");
				String contactperson=results.getString("contactperson");
				String gender=results.getString("gender");
				String phone=results.getString("phone");
				String addressdetail=results.getString("addressdetail");
				int userno=results.getInt("userno");
				Address s=new Address(addressno,contactperson,gender,phone,addressdetail,userno);
				address.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return address;	
	}
	
	public int deleteAddressInfo(int no){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from address where addressno="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return i;
    }
	
	
	public int insertAddressInfo(Address s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into address(addressno,contactperson,gender,phone,addressdetail,userno) values(?,?,?,?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, s.getAddressno());
			pst.setString(2, s.getContactperson());
			pst.setString(3, s.getGender());
			pst.setString(4, s.getPhone());
			pst.setString(5, s.getAddressdetail());
			pst.setInt(6, s.getUserno());

			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateAddress(Address s){
		int i=0;
		Connection con=getConn();
		String sql="update address set "
				+"contactperson='"+s.getContactperson()
				+"',gender='"+s.getGender()
				+"',phone='"+s.getPhone()
				+"',addressdetail='"+s.getAddressdetail()
				+"',userno="+s.getUserno()
				+" where"+ " addressno="+s.getAddressno();
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;	
		
	}
	
	
	public List<Order> findOrderInfo(String key){
		List<Order> order=new ArrayList<Order>();
		Connection conn=getConn();
		String sql = "select * from orderdata where orderid like '%"+key+"%' or userno like '%"+key+"%' order by orderid desc";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				
				int orderid=results.getInt("orderid");
				int userno=results.getInt("userno");
				int restaurantid=results.getInt("restaurantid");
				String username=results.getString("username");
				String restaurantname=results.getString("restaurantname");
				String orderdetails=results.getString("orderdetails");
				float totalprice=results.getFloat("totalprice");
				String starttime=results.getString("starttime");
				String orderstatus=results.getString("orderstatus");
				int addressno=results.getInt("addressno");
				String orderaddress=results.getString("orderaddress");
				
				
				Order s=new Order(orderid,userno,restaurantid,username,restaurantname,orderdetails,totalprice,starttime,orderstatus,addressno,orderaddress);
				order.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;	
	}
	

	
	public int deleteOrderInfo(int no){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from orderdata where orderid="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return i;
    }
	
	
	public int insertOrderInfo(Order s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into orderdata(orderid,userno,restaurantid,username,restaurantname,orderdetails,totalprice,starttime,orderstatus,addressno,orderaddress) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, s.getOrderid());
			pst.setInt(2, s.getUserno());
			pst.setInt(3, s.getRestaurantid());
			pst.setString(4, s.getUsername());
			pst.setString(5, s.getRestaurantname());
			pst.setString(6, s.getOrderdetails());
			pst.setFloat(7, s.getTotalprice());
			pst.setString(8, s.getStarttime());
			pst.setString(9, s.getOrderstatus());
			pst.setInt(10, s.getAddressno());
			pst.setString(11, s.getOrderaddress());
			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateOrder(Order s){
		int i=0;
		Connection con=getConn();
		String sql="update orderdata set "
				+"userno="+s.getUserno()
				+",restaurantid="+s.getRestaurantid()
				+",username='"+s.getUsername()
				+"',restaurantname='"+s.getRestaurantname()
				+"',orderdetails='"+s.getOrderdetails()
				+"',totalprice="+s.getTotalprice()
				+",starttime='"+s.getStarttime()
				+"',orderstatus='"+s.getOrderstatus()
				+"',addressno="+s.getAddressno()
				+",orderaddress='"+s.getOrderaddress()
				+"' where"+ " orderid="+s.getOrderid();
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public List<Feedback> findFeedbackInfo(String key){
		List<Feedback> feedback=new ArrayList<Feedback>();
		Connection conn=getConn();
		String sql = "select * from feedback where feedbackno like '%"+key+"%' or userno like '%"+key+"%' order by feedbackno desc";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				
				int feedbackno=results.getInt("feedbackno");
				String feedbackdetail=results.getString("feedbackdetail");
				int userno=results.getInt("userno");
				String username=results.getString("username");
				String contactdetail=results.getString("contactdetail");
	
				
				Feedback s=new Feedback(feedbackno,feedbackdetail,userno,username,contactdetail);
				feedback.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return feedback;	
	}
	

	
	public int deleteFeedbackInfo(int no){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from feedback where feedbackno="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return i;
    }
	
	
	public int insertFeedbackInfo(Feedback s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into feedback(feedbackno,feedbackdetail,userno,username,contactdetail) values(?,?,?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, s.getFeedbackno());
			pst.setString(2, s.getFeedbackdetail());
			pst.setInt(3, s.getUserno());
			pst.setString(4, s.getUsername());
			pst.setString(5, s.getContactdetail());
		
			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateFeedback(Feedback s){
		int i=0;
		return i;
	}
	
	
	public List<Shopcomment> findShopcommentInfo(String key){
		List<Shopcomment> shopcomment=new ArrayList<Shopcomment>();
		Connection conn=getConn();
		String sql = "select * from shopcomment where shopcommentno like '%"+key+"%' or restaurantid like '%"+key+"%' order by shopcommentno desc";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				
				int shopcommentno=results.getInt("shopcommentno");
				int restaurantid=results.getInt("restaurantid");
				String content=results.getString("content");
				String commenttime=results.getString("commenttime");
				int userno=results.getInt("userno");
				String username=results.getString("username");
				
				Shopcomment s=new Shopcomment(shopcommentno,restaurantid,content,commenttime,userno,username);
				shopcomment.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shopcomment;	
	}
	

	
	public int deleteShopcommentInfo(int no){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from feedback where feedbackno="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return i;
    }
	
	
	public int insertShopcommentInfo(Shopcomment s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into shopcomment(shopcommentno,restaurantid,content,commenttime,userno,username) values(?,?,?,?,?,?)";
		try {
			
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, s.getShopcommentno());
			pst.setInt(2, s.getRestaurantid());
			pst.setString(3, s.getContent());
			pst.setString(4, s.getCommenttime());
			pst.setInt(5, s.getUserno());
			pst.setString(6, s.getUsername());
		
			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateShopcomment(Shopcomment s){
		int i=0;
		return i;
	}
	
	
	public List<Trend> findTrendInfo(String key){
		List<Trend> trend=new ArrayList<Trend>();
		Connection conn=getConn();
		String sql = "select * from trend where trendno like '%"+key+"%' or userno like '%"+key+"%' order by trendno desc";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int trendno=results.getInt("trendno");
				int userno=results.getInt("userno");
				String username=results.getString("username");
				String trendtitle=results.getString("trendtitle");
				String trendcontent=results.getString("trendcontent");
				String releasetime=results.getString("releasetime");
				Trend s=new Trend(trendno,userno,username,trendtitle,trendcontent,releasetime);
				trend.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trend;	
	}
	
	public int deleteTrendInfo(int no){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from trend where trendno="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return i;
    }
	
	
	public int insertTrendInfo(Trend s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into trend(trendno,userno,username,trendtitle,trendcontent,releasetime) values(?,?,?,?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, s.getTrendno());
			pst.setInt(2, s.getUserno());
			pst.setString(3, s.getUsername());
			pst.setString(4, s.getTrendtitle());
			pst.setString(5, s.getTrendcontent());
			pst.setString(6, s.getReleasetime());
			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateTrend(Trend s){
		int i=0;
		/*
		 * Connection con=getConn(); String
		 * sql="update restaurant set restaurantname='"+s.getRestaurantname()+
		 * "',introduction='"+s.getIntroduction()+"',restaurantaddress='"+s.
		 * getRestaurantaddress()+"'where" + " restaurantid="+s.getRestaurantid(); try {
		 * PreparedStatement pst=con.prepareStatement(sql); i=pst.executeUpdate();
		 * pst.close(); con.close(); } catch (SQLException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); }
		 */
		return i;	
		
	}
	
	
	public List<Trendcomment> findTrendcommentInfo(String key){
		List<Trendcomment> trendcomment=new ArrayList<Trendcomment>();
		Connection conn=getConn();
		String sql = "select * from trendcomment where trendno like '%"+key+"%' order by trendcommentno desc";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				
				int trendcommentno=results.getInt("trendcommentno");
				int trendno=results.getInt("trendno");
				String content=results.getString("content");
				String commenttime=results.getString("commenttime");
				int userno=results.getInt("userno");
				String username=results.getString("username");
				
				Trendcomment s=new Trendcomment(trendcommentno,trendno,content,commenttime,userno,username);
				trendcomment.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trendcomment;	
	}
	

	
	public int deleteTrendcommentInfo(int no){
    	int i=0;
    	Connection con=getConn();
    	String sql="delete from trendcomment where trendcommentno="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return i;
    }
	
	
	public int insertTrendcommentInfo(Trendcomment s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into trendcomment(trendcommentno,trendno,content,commenttime,userno,username) values(?,?,?,?,?,?)";
		try {
			
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, s.getTrendcommentno());
			pst.setInt(2, s.getTrendno());
			pst.setString(3, s.getContent());
			pst.setString(4, s.getCommenttime());
			pst.setInt(5, s.getUserno());
			pst.setString(6, s.getUsername());
		
			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}	
	
	
	public List<User> findFansInfo(String key){
		List<User> user=new ArrayList<User>();
		Connection conn=getConn();
		String sql ="SELECT user.* from user,fans WHERE user.userno=fans.fansuserno AND fans.userno="+key;
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int userno=results.getInt("userno");
				String username=results.getString("username");
				String password=results.getString("password");
				String tempgender=results.getString("gender");
				String gender="ÄÐ";
				if(tempgender.equals("1")) {
					gender="Å®";
				}
				int age=results.getInt("age");
				float height=results.getFloat("height");
				float weight=results.getFloat("weight");
				float targetweight=results.getFloat("targetweight");
				String preference=results.getString("preference");
				User s=new User(userno,username,password,gender,age,height,weight,preference,targetweight);
				user.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;	
	}
	
	
	public List<Focus> Checkfocus(String usernokey,String focususernokey){
		List<Focus> focus=new ArrayList<Focus>();
		Connection conn=getConn();
		String sql ="SELECT * from focus where userno="+usernokey+" and focususerno="+focususernokey;
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int focusid=results.getInt("focusid");
				int userno=results.getInt("userno");
				int focususerno=results.getInt("focususerno");

				Focus s=new Focus(focusid,userno,focususerno);
				focus.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return focus;	
	}
	
	
	public int cancelFocus(int no,int no2){
    	int i=0,i2=0;
    	Connection con=getConn();
    	String sql="delete from focus where userno="+no+" and focususerno="+no2;
    	String sql2="delete from fans where userno="+no2+" and fansuserno="+no;
    	try {
			PreparedStatement pst=con.prepareStatement(sql);
			i=pst.executeUpdate();
			pst.close();
			
			PreparedStatement pst2=con.prepareStatement(sql2);
			i2=pst2.executeUpdate();
			pst2.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return i;
    }
	
	public int insertFocusInfo(Fans s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into fans(fansid,userno,fansuserno) values(?,?,?)";
		String sql2="insert into focus(focusid,userno,focususerno) values(?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, s.getFansid());
			pst.setInt(2, s.getFansuserno());
			pst.setInt(3, s.getUserno());
			int i=pst.executeUpdate();
			pst.close();
			
			PreparedStatement pst2=con.prepareStatement(sql2);
			pst2.setInt(1, 0);
			pst2.setInt(2, s.getUserno());
			pst2.setInt(3, s.getFansuserno());
			int i2=pst2.executeUpdate();
			pst2.close();
			
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public List<User> findFocusInfo(String key){
		List<User> user=new ArrayList<User>();
		Connection conn=getConn();
		String sql ="SELECT user.* from user,focus WHERE user.userno=focus.focususerno AND focus.userno="+key;
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int userno=results.getInt("userno");
				String username=results.getString("username");
				String password=results.getString("password");
				String tempgender=results.getString("gender");
				String gender="ÄÐ";
				if(tempgender.equals("1")) {
					gender="Å®";
				}
				int age=results.getInt("age");
				float height=results.getFloat("height");
				float weight=results.getFloat("weight");
				float targetweight=results.getFloat("targetweight");
				String preference=results.getString("preference");
				User s=new User(userno,username,password,gender,age,height,weight,preference,targetweight);
				user.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;	
	}
	
	
	public List<Message> findMessageInfo(String key){
		List<Message> message=new ArrayList<Message>();
		Connection conn=getConn();
		String sql = "select * from message where messageid like '%"+key+"%' or receiveruserno like '%"+key+"%' order by messageid desc";
		try {
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet results=pst.executeQuery();
			while(results.next()){
				int messageid=results.getInt("messageid");
				int senderuserno=results.getInt("senderuserno");
				String sendername=results.getString("sendername");
				int receiveruserno=results.getInt("receiveruserno");
				String messagecontent=results.getString("messagecontent");
				String sendtime=results.getString("sendtime");
				Message s=new Message(messageid,senderuserno,sendername,receiveruserno,messagecontent,sendtime);
				message.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;	
	}
	
	public int insertMessageInfo(Message s){
		if(s==null) return 0;
		Connection con=getConn();
		String sql="insert into message(messageid,senderuserno,sendername,receiveruserno,messagecontent,sendtime) values(?,?,?,?,?,?)";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, s.getMessageid());
			pst.setInt(2, s.getSenderuserno());
			pst.setString(3, s.getSendername());
			pst.setInt(4, s.getReceiveruserno());
			pst.setString(5, s.getMessagecontent());
			pst.setString(6, s.getSendtime());
			int i=pst.executeUpdate();
			pst.close();
			con.close();
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
