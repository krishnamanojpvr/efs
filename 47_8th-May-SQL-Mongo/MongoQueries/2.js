/*
Identify food items that were ordered more than 3 times in total across all orders.
Display food item names along with their total order counts, sorted from most to 
least popular and by food name in ascending order .


Sample output:
--------------
[
  { _id: 'F103', orderCount: 7, food_name: 'Hyderabadi Biryani' },
  { _id: 'F109', orderCount: 7, food_name: 'Gulab Jamun' }
]


Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	db -> Refers to the database.
    	<collection> -> Your collection name
	
*/
	
printjson(db.Orders.aggregate([
    {
        $lookup : {
            from : "FoodItems",
            localField : "food_items.food_id",
            foreignField : "food_id",
            as : "food"
        }
    },
    {
        $unwind : "$food"
    },
    {
        $group : {
            _id : "$food.food_id" , orderCount : {$sum : 1},food_name : {$first : "$food.name" }
        }
    },
    {
      $match : {orderCount : {$gt:3}}  
    },
    {
        $sort : {orderCount : -1, food_name : 1}
    }
    ])
    
)
