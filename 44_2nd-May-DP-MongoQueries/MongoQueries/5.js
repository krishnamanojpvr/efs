// Write a MongoDB aggregation query to calculate total hospital revenue grouped
// by whether bills were covered by insurance or not.

// Sample output:
// --------------
// [
//   { totalRevenue: 2000, insuranceCovered: true },
//   { totalRevenue: 1000, insuranceCovered: false }
// ]

// Query Reference:
// -------------------
// printjson() : JS library function to display the JSON Object data.
//     In db.<collection>.find()/aggregate():
//     	db -> Refers to the database.
//     	<collection> -> Your collection name

// */

printjson(
  db.billing.aggregate([
    {
      $group: {
        _id: "$insuranceCovered",
        totalRevenue: { $sum: "$totalAmount" },
      },
    },
    {
      $project: {
        _id: 0,
        insuranceCovered: "$_id",
        totalRevenue: 1,
      },
    },
  ])
);
