<?php

$my_dir = dirname(__FILE__);
require_once $my_dir . '/../persistence/persistence.php';
require_once $my_dir . '/../controller/FundingController.php';
require_once $my_dir . '/../model/URLMS.php';
require_once $my_dir . '/../model/Lab.php';
require_once $my_dir . '/../model/FinancialReport.php';
require_once $my_dir . '/../model/Expense.php';
require_once $my_dir . '/../model/FundingAccount.php';


?>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>URLMS - Funding</title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../style/TableView.css">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
	</head>
	
	<body>
	
		<!--  Nav Bar -->
		<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
	  		<a class="navbar-brand" href="../index.php">
	  			<img src="../image/URLMS_Logo.png" width="40" height="40" class="d-inline-block align-top" alt="">
	  			URLMS
	  		</a>
	 		 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	  			 <span class="navbar-toggler-icon"></span>
	 		 </button>
	  			<div class="collapse navbar-collapse" id="navbarNav">
		    		<ul class="navbar-nav mr-auto">
		      			<!-- li class="nav-item active">
		        			<a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
		      			</li> -->
		      			<li class="nav-item">
		        			<a class="nav-link" href="StaffView.php">Staff</a>
		      			</li>
		      			<li class="nav-item">
		        			<a class="nav-link" href="InventoryView.php">Inventory</a>
		      			</li>
		      			<li class="nav-item">
		        			<a class="nav-link" href="FundingView.php">Funding</a>
		      			</li>
		      		</ul>
		      		<ul class="nav-bar">	
		      			<li class="nav-item" style="text-decoration: none; list-style: none;">
		      				<a class="nav-link" href=#>Help</a>
		      			</li>
		    		</ul>
	  			</div>
		</nav>
		<br><br><br><br><br>
	
		<!-- Content -->
		<div class="container">
			<h1 style="text-align: center; color: rgb(220,53,69);" id="top">Funding</h1>
			<hr>
			<br><br>
			<!-- Bootstrap Template -->
	  		<!-- 	<div class="row">
	    				<div class="col">
	     					1 of 2
	  					</div>
	   					<div class="col">
	      					2 of 2
	    				</div>
	  				</div>
	  				<div class="row">
	    				<div class="col">
	      					1 of 3
	    				</div>
	    				<div class="col">
	      					2 of 3
	    				</div>
	    				<div class="col">
	      					3 of 3
	    				</div>
	  				</div> -->
			
			<div id="accordion" role="tablist">
				  <div class="card">
				    <div class="card-header" role="tab" id="headingOne">
				      <h5 class="mb-0">
				        <a class="collapsed" data-toggle="collapse" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne" style="color: rgb(220,53,69); text-decoration: none;">
				          View Net Balance
				        </a>
				      </h5>
				    </div>
				    <div id="collapseOne" class="collapse" role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">
				      <div class="card-body">
				      		<form action="FundingRequest.php" method="get">
								<br>
								<h3>View Net Balance</h3>
								<input type="hidden" name="action" value="10/10" />
						 		<input type="submit" value="View Net Balance!" />
								<br>
							</form>
				      </div>
				    </div>
				  </div>
				  <!-- To be removed -->
<!-- 				  <div class="card"> -->
<!-- 				    <div class="card-header" role="tab" id="headingTwo"> -->
<!-- 				      <h5 class="mb-0"> -->
<!-- 				        <a class="collapsed" data-toggle="collapse" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" style="color: rgb(220,53,69); text-decoration: none;">
<!-- 				          View Funding Accounts -->
<!-- 				        </a> -->
<!-- 				      </h5> -->
<!-- 				    </div> -->
<!-- 				    <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo" data-parent="#accordion"> -->
<!-- 				      <div class="card-body"> -->
<!-- 				      		<form action="FundingRequest.php" method="get"> -->
<!-- 								<br> -->
<!-- 								<h3>View Funding Accounts</h3> -->
<!-- 								<input type="hidden" name="action" value="9/10" /> -->
<!-- 						 		<input type="submit" value="View Accounts!" /> -->
<!-- 								<br> -->
<!-- 							</form> -->
<!-- 				      </div> -->
<!-- 				    </div> -->
<!-- 				  </div> -->
				  
				  <div class="card">
				    <div class="card-header" role="tab" id="headingThree">
				      <h5 class="mb-0">
				        <a class="collapsed" data-toggle="collapse" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" style="color: rgb(220,53,69); text-decoration: none;">
				          Add Funding Account
				        </a>
				      </h5>
				    </div>
				    <div id="collapseThree" class="collapse" role="tabpanel" aria-labelledby="headingThree" data-parent="#accordion">
				      <div class="card-body">
				        <form action="FundingRequest.php" method="get">
							<br>
							<h3>Add Funding Account</h3>
							<input type="hidden" name="action" value="1/10" />
							Type<input type="text" name="addtype" value=""/><br>
							Balance<input type="text" name="addbalance" value=""/><br>
							<input type="submit" value="Add Funding Account!"/>
							<br>
						</form>
				      </div>
				    </div>
				  </div>
				  
				  <div class="card">
				    <div class="card-header" role="tab" id="headingFour">
				      <h5 class="mb-0">
				        <a class="collapsed" data-toggle="collapse" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour" style="color: rgb(220,53,69); text-decoration: none;">
				          Remove Funding Account
				        </a>
				      </h5>
				    </div>
				    <div id="collapseFour" class="collapse" role="tabpanel" aria-labelledby="headingFour" data-parent="#accordion">
				      <div class="card-body">
				        <form action="FundingRequest.php" method="get">
							<br>
							<h3>Remove Funding Account</h3>
							<input type="hidden" name="action" value="4/10" />
							Type<input type="text" name="removetype" value=""/><br>
							<input type="submit" value="Remove Funding Account!"/>
							<br>
						</form>
				      </div>
				    </div>
				  </div>
				  
				  <div class="card">
				    <div class="card-header" role="tab" id="headingFive">
				      <h5 class="mb-0">
				        <a class="collapsed" data-toggle="collapse" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive" style="color: rgb(220,53,69); text-decoration: none;">
				          Generate Financial Report
				        </a>
				      </h5>
				    </div>
				    <div id="collapseFive" class="collapse" role="tabpanel" aria-labelledby="headingFive" data-parent="#accordion">
				      <div class="card-body">
				        <form action="FundingRequest.php" method="get">
							<br>
							<h3>Generate Financial Report of an Account</h3>
							Account Type<input type="text" name="accounttype" value=""/><br>
							<input type="hidden" name="action" value="11/10" />
					 		<input type="submit" value="View Accounts!" />
							<br>
						</form>
				      </div>
				    </div>
				  </div>
				  
				  <!-- To be removed when table becomes clickable -->
				  <div class="card">
				    <div class="card-header" role="tab" id="headingSix">
				      <h5 class="mb-0">
				        <a class="collapsed" data-toggle="collapse" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix" style="color: rgb(220,53,69); text-decoration: none;">
				          View Account and Account Net Balance
				        </a>
				      </h5>
				    </div>
				    <div id="collapseSix" class="collapse" role="tabpanel" aria-labelledby="headingSix" data-parent="#accordion">
				      <div class="card-body">
				        <form action="FundingRequest.php" method="get">
							<br>
							<h3>View/Edit Account and Account Net Balance</h3>
							Type<input type="text" name="viewtype" value=""/><br>
							<input type="hidden" name="action" value="2/10" />
							<input type="submit" value="View Net Balance!"/>
							<br>
						</form>
				      </div>
				    </div>
				  </div>
				  
				  <div class="card">
				    <div class="card-header" role="tab" id="headingSeven">
				      <h5 class="mb-0">
				        <a class="collapsed" data-toggle="collapse" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven" style="color: rgb(220,53,69); text-decoration: none;">
				          Add Transaction
				        </a>
				      </h5>
				    </div>
				    <div id="collapseSeven" class="collapse" role="tabpanel" aria-labelledby="headingSeven" data-parent="#accordion">
				      <div class="card-body">
				        <form action="FundingRequest.php" method="get">
							<br>
							<h3>Add Transaction</h3>
							<input type="hidden" name="action" value="3/10" />
							Account Type<input type="text" name="account" value=""/><br>
							Expense Type<input type="text" name="expensetype" value=""/><br>
							Amount<input type="text" name="amount" value=""/><br>
							<input type="radio" name="type" value="fund"/>Fund<br>
							<input type="radio" name="type" value="expense"/>Expense<br>
							Date<input type="text" name="date" value=""/><br>
							<input type="submit" value="Add Transation!!!"/>
							<br>
						</form>
				      </div>
				    </div>
				  </div>
				  
				  <div class="card">
				    <div class="card-header" role="tab" id="headingEight">
				      <h5 class="mb-0">
				        <a class="collapsed" data-toggle="collapse" href="#collapseEight" aria-expanded="false" aria-controls="collapseEight" style="color: rgb(220,53,69); text-decoration: none;">
				          Payday
				        </a>
				      </h5>
				    </div>
				    <div id="collapseEight" class="collapse" role="tabpanel" aria-labelledby="headingEight" data-parent="#accordion">
				      <div class="card-body">
				        <form action="FundingRequest.php" method="get">
					 		<br>
					 		<h3>Payday</h3>
					 		<input type="hidden" name="action" value="5/10" />
					 		<input type="submit" class="btn btn-success" value="Payday">
					 		<br>	
					 	</form>
				      </div>
				    </div>
				  </div>
				  
				  
				  <div class="card">
				    <div class="card-header" role="tab" id="headingFour">
				      <h5 class="mb-0" style="color: rgb(220,53,69)">
				        View Accounts
				      </h5>
				    </div>
			      	<div class="card-body">
						<div class="container-fluid">
							<table class="table table-hover" style="width: 100%;">
								
								<thread>
								<tr>
									<th>Account</th>
									<th>Net balance</th>
									<th>Latest Expense</th>
								</tr>
								</thread>
								<tbody>
								<?php 
								
								$urlms = (new Persistence())->loadDataFromStore();
								
								
								foreach ($urlms->getLab_index(0)->getFundingAccounts() as $account){
									$latestExpense = "";
									if($account->hasExpenses()){
										$expense = $account->getExpense_index(sizeof($account->getExpenses())-1);
										$latestExpense = $expense->getType() .", $". $expense->getAmount();
									}else{
										$latestExpense = "None";
									}
									
									echo "<tr><td><button type=\"button\" class=\"btn btn-outline-primary\">" . $account->getType() . "</button></td>
										<td>$". $account->getBalance() ."</td>
										<td>" . $latestExpense . "</td>
										</tr>";
								}?>
								</tbody>
							</table>
							</div>
						</div>
				    </div>
				    
				  </div>
				  
				  <br><br>	
						<div class="row">
				    		<div class="col-sm-2">
				     			<a href="../index.php" style="color: white;text-decoration: none;">
									<button type="button" class="btn btn-danger" data-toggle="tooltip" data-placement="bottom" title="Go back to homepage">
										Back to homepage
									</button>
								</a>
				  			</div>
				  			<div class="col-sm-8">
						    </div>
				   			<div class="col-sm-2">
				      			<a href="#top" style="color: white;text-decoration: none;">
									<button type="button" class="btn btn-danger" data-toggle="tooltip" data-placement="bottom" title="Go back to top of page">
										Back to top
									</button>
								</a>
				    		</div>
				  		</div>
					<br><br>
							
		</div>
		
		<!-- Footer -->
		<footer>
			<div class="card bg-light mb-12">
	  				<div class="card-body">
	  				<center>
	    				<p class="card-text">Montreal, QC, Canada</p>
	    				<p class="card-text">Copyright &copy; URLMS Team 8, 2017</p>
	    				<p class="card-text">Created by Feras Al Taha and Justin Lei</p>
	    			</center>
	  				</div>
			</div>
		</footer>	
	</body>
</html>