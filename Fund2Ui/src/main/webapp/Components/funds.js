/**
 * 
 */
$(document).ready(function()
{ 
	
	  
	$("#alertSuccess").hide(); 
 	$("#alertError").hide(); 
});

$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
 	$("#alertSuccess").text(""); 
 	$("#alertSuccess").hide(); 
 	$("#alertError").text(""); 
 	$("#alertError").hide(); 

	// Form validation-------------------
	var status = validateFundForm(); 
	if (status != true) 
 	{ 
 		$("#alertError").text(status); 
 		$("#alertError").show(); 
 		return; 
 	} 
		
	// If valid------------------------
	var type = ($("#hidFundIDSave").val() == "") ? "POST" : "PUT"; 
 	
	$.ajax( 
 	{ 
 		url : "FundsAPI", 
 		type : type, 
 		data : $("#formFund").serialize(), 
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onItemSaveComplete(response.responseText, status); 
 		} 
 	}); 
});



function onFundSaveComplete(response, status)
{ 
	if (status == "success") 
 	{ 
 		var resultSet = JSON.parse(response); 
 		if (resultSet.status.trim() == "success") 
 		{ 
 			$("#alertSuccess").text("Successfully saved."); 
 			$("#alertSuccess").show(); 
 			
			$("#divFundsGrid").html(resultSet.data); 
 		} else if (resultSet.status.trim() == "error") 
		{ 
 			$("#alertError").text(resultSet.data); 
			$("#alertError").show(); 
 		} 
 
		} else if (status == "error") 
 		{ 
 			$("#alertError").text("Error while saving."); 
 			$("#alertError").show(); 
 		} else
 		{ 
 			$("#alertError").text("Unknown error while saving.."); 
 			$("#alertError").show(); 
 		} 
 
		$("#hidFundIDSave").val(""); 
 		$("#formFund")[0].reset(); 
}


$(document).on("click", ".btnUpdate", function(event)
{ 
	$("#hidFundIDSave").val($(this).data("FundID")); 
 	$("#ProjectID").val($(this).closest("tr").find('td:eq(0)').text()); 
 	$("#ProjectName").val($(this).closest("tr").find('td:eq(1)').text()); 
 	$("#ReceiverID").val($(this).closest("tr").find('td:eq(2)').text()); 
 	$("#TimeRange").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#TotalAmount").val($(this).closest("tr").find('td:eq(4)').text()); 
	$("#Status").val($(this).closest("tr").find('td:eq(5)').text()); 
});


$(document).on("click", ".btnRemove", function(event)
{ 
 	$.ajax( 
 	{ 
 		url : "FundsAPI", 
 		type : "DELETE", 
 		data : "FundID=" + $(this).data("Fundid"),
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onItemDeleteComplete(response.responseText, status); 
 		} 
 	}); 
});

function onFundDeleteComplete(response, status)
{ 
	if (status == "success") 
	 { 
 		var resultSet = JSON.parse(response); 
 
			if (resultSet.status.trim() == "success") 
 			{ 
 				$("#alertSuccess").text("Successfully deleted."); 
 				$("#alertSuccess").show(); 
 
				$("#divFundsGrid").html(resultSet.data); 
 			
			} else if (resultSet.status.trim() == "error") 
 			{ 
 				$("#alertError").text(resultSet.data); 
 				$("#alertError").show(); 
 			} 
 		
		} else if (status == "error") 
 		{ 
 			$("#alertError").text("Error while deleting."); 
 			$("#alertError").show(); 
 		
		} else
 		{ 
 			$("#alertError").text("Unknown error while deleting.."); 
 			$("#alertError").show(); 
 		} 
}
