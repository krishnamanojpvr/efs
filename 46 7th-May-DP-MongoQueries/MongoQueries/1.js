/*
Find the top 3 restaurants based on average user ratings.
Display restaurant names, average ratings, and total number of reviews.


Sample output:
--------------
[
  {
    _id: 'R103',
    averageRating: 4.428571428571429,
    reviewCount: 7,
    restaurant_name: 'Minerva Coffee Shop'
  },
  {
    _id: 'R105',
    averageRating: 4.428571428571429,
    reviewCount: 7,
    restaurant_name: 'MTR'
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
          from : "Reviews",
          localField : "restaurant_id",
          foreignField : "restaurant_id",
          as : "reviews"
      }
  },
  {
      $unwind : "$reviews"
  },
  {
      $group : {
          _id : "$restaurant_id", averageRating : {$avg : "$reviews.rating" },reviewCount : {$sum : 1}, restaurant_name : {$first : "$name"}
      }
  },
  {
      $sort : {averageRating : -1,reviewCount:-1}
  },
  {
      $limit : 3
  }
  ])
  
)
