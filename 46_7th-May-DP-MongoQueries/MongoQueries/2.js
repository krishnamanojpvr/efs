/*
Calculate total revenue and total orders for each restaurant.
Display restaurant names along with their total revenue and number of orders.
Sort by highest revenue first.



Sample output:
--------------
[
  {
    _id: 'R102',
    totalRevenue: 8273,
    totalOrders: 11,
    restaurant_name: 'Chutneys'
  },
  {
    _id: 'R103',
    totalRevenue: 4935,
    totalOrders: 7,
    restaurant_name: 'Minerva Coffee Shop'
  }
]


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	db -> Refers to the database.
    	<collection> -> Your collection name
	
*/
	
	
printjson(db.Restaurants.aggregate([
  {
      $lookup : {
          from : "Orders",
          localField : "restaurant_id",
          foreignField : "restaurant_id",
          as : "orders"
      }
  },
  {
      $unwind : "$orders"
  },
  {
      $group : {
          _id : "$restaurant_id", totalRevenue : {$sum : "$orders.total_amount" },totalOrders : {$sum : 1}, restaurant_name : {$first : "$name"}
      }
  },
  {
      $sort : {totalRevenue : -1}
  }
  ])
  
)

