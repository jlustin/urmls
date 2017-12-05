
<?php $my_dir = dirname(__FILE__);
	require_once $my_dir . '/persistence/persistence.php';
	require_once $my_dir . '/model/URLMS.php';
	require_once $my_dir . '/model/Lab.php';
	require_once $my_dir . '/model/StaffMember.php';
	require_once $my_dir . '/model/ResearchRole.php';
	require_once $my_dir . '/model/ResearchAssociate.php';
	require_once $my_dir . '/model/ResearchAssistant.php';
	require_once $my_dir . '/model/ProgressUpdate.php';
	require_once $my_dir . '/model/InventoryItem.php';
	require_once $my_dir . '/model/SupplyType.php';
	require_once $my_dir . '/model/Equipment.php';
	require_once $my_dir . '/model/FinancialReport.php';
	require_once $my_dir . '/model/Expense.php';
	require_once $my_dir . '/model/FundingAccount.php';
	
?>

<html>
	<head>
		<title>URLMS</title>
		<!-- https://getbootstrap.com/docs/4.0/content/tables/ -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
		<link rel="stylesheet" type="text/css" href="style/style.css">
	</head>
	<body background="../image/lab_background.jpg" style="background-size: 100%; background-attachment: fixed; background-position: center;
    background-repeat: no-repeat;
    background-size: cover;">
<!-- 	<h1 style="text-align: center"><a href="index.php">University Research Lab Management System</a></h1> -->
	
	<!--  Nav Bar -->
	<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="../index.php"> <img
			src="../image/URLMS_Logo.png" width="40" height="40"
			class="d-inline-block align-top" alt=""> University Research Lab
			Management System
		</a>
	</nav>
	<br>
	<br>
	<br>
	<br>
	<br>
	<!-- Bootstrap Container -->
	<div class="container">
		<!-- Bootstrap Continer Template -->
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
  		<?php $urlms = (new Persistence("Persistence/data.txt"))->loadDataFromStore();?>
  		<!-- Carousel -->
		<br>
  		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  		   <ol class="carousel-indicators">
			    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
			    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		   </ol>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <div class="jumbotron home-page-elem" style="text-align: center;">
		      
				  <h1 class="display-3">Staff</h1>
				  <hr class="my-4" style="width: 50%;">
				  <p>
				  		Total Number of Staff: <?php echo $urlms->getLab_index(0)->numberOfStaffMembers();?>
				  		<br>
				  		<?php
				  		$count = 0;
				  		$staffs = $urlms->getLab_index(0)->getStaffMembers();
				  		foreach ($staffs as $s){
				  			if (!($s->hasProgressUpdates())){
				  				$count++;
				  			}
				  		}
				  		// warning for not progress updates
			  			if ($count == 1){
			  			?>
			  				<br>
			  				 <img src="../image/warning.png" alt="Warning Sign" style="height: auto; width: auto; max-height: 17.5px; max-width: 17.5px;">
			  				<?php echo $count . " staff member has no progress update!";
			  			}
			  			else if($count > 1){
			  				?>
			  				<br>
			  				<img src="../image/warning.png" alt="Warning Sign" style="height: auto; width: auto; max-height: 17.5px; max-width: 17.5px;">
			  				<?php echo $count . " staff members have no progress update!";
			  			}
			  			else echo "<br>";
				  		?>
				  		<br>
				  		<br>
				  </p>
						<p class="lead">
							<a class="btn btn-danger btn-lg" href="View/StaffView.php"
								role="button">Go to Staff</a>
						</p>

					</div>
				</div>
				<div class="carousel-item">
					<div class="jumbotron home-page-elem" style="text-align: center;">
						<h1 class="display-3">Inventory</h1>
						<hr class="my-4" style="width: 50%;">
						<p>
				  		Total Number of Inventory Items: <?php echo $urlms->getLab_index(0)->numberOfInventoryItems() . "<br>";?>
				  		Total Value of Inventory Items:
<
				  		<?php 
				  			$totalValue = 0; $countEQ = 0; $countSP = 0;
				  			$inventoryItems = $urlms->getLab_index(0)->getInventoryItems();
				  			foreach ($inventoryItems as $inventoryItem){
				  				$totalValue = $totalValue + $inventoryItem->getCost();
				  				/**
				  				 * TO:DO getIsDamaged doesnt seem to work 
				  				 */ 
				  				if(get_class($inventoryItem) == "Equipement"){
				  					if($inventoryItem->getIsDamaged() == true){
				  						$countEQ++;
				  					}
				  				}
				  				/**
				  				 * TO:DO getQuantity doesnt seem to "exist"
				  				 */ 
// 				  				else {
// 				  					if($inventoryItem->getQuantity() < 10){
// 				  						$countSP++;
// 				  					}
// 				  				}
				  			}
				  			echo $totalValue . " $";
				  			// warning for equipement damage
					  		if ($countEQ == 1){
				  				?>
				  				<br>
				  				 <img src="../image/warning.png" alt="Warning Sign" style="height: auto; width: auto; max-height: 17.5px; max-width: 17.5px;">
				  				<?php echo $countEQ . " equipement is damaged!";
				  			}
				  			else if($countEQ > 1){
				  				?>
				  				<br>
				  				<img src="../image/warning.png" alt="Warning Sign" style="height: auto; width: auto; max-height: 17.5px; max-width: 17.5px;">
				  				<?php echo $countEQ . " equipements are damaged!";
				  			}
				  			else echo "<br>";
				  			
				  			// warning for supply quantity
				  			if ($countSP == 1){
				  				?>
				  				<br>
				  				 <img src="../image/warning.png" alt="Warning Sign" style="height: auto; width: auto; max-height: 17.5px; max-width: 17.5px;">
				  				<?php echo $countSP . " supply is running low!";
				  			}
				  			else if($countSP > 1){
				  				?>
				  				<br>
				  				<img src="../image/warning.png" alt="Warning Sign" style="height: auto; width: auto; max-height: 17.5px; max-width: 17.5px;">
				  				<?php echo $countSP . " supplies are running low!";
				  			}
				  			else echo "<br>";?>
				  		<br>
				  </p>
				  <p class="lead">
				    <a class="btn btn-danger btn-lg" href="View/InventoryView.php" role="button">Go to Inventory</a>
				  </p>
			  </div>
		    </div>
		    <div class="carousel-item">
		      <div class="jumbotron home-page-elem" style="text-align: center;">
				  <h1 class="display-3">Funding</h1>
				  <hr class="my-4" style="width: 50%;">
				  <p>
				  		<!-- Checking total net balance of laboratory -->
				  		Net Balance of Laboratory: 
				  		<?php 
				  			$netBalance = 0;
				  			$accounts = $urlms->getLab_index(0)->getFundingAccounts();
				  			foreach ($accounts as $a){
				  				$netBalance = $netBalance + $a->getBalance();
				  			}
				  			echo $netBalance . " $ <br>";
				  		?>
				  		<!-- Checking total number of accounts -->
>				  		Total Number of Accounts: <?php echo $urlms->getLab_index(0)->numberOfFundingAccounts();?>
				  		<br>
				  		<!-- Checking how many accounts have a negative balance -->
				  		<?php 
				  			$balance = 0; $count = 0;
				  			foreach ($accounts as $a){
				  				$balance = $a->getBalance();
				  				if ($balance < 0){
				  					$count ++;
				  				}
				  			}
				  			
				  			if ($count == 1){
				  				?>
				  				<div class="alert alert-warning alert-dismissible fade show"
							role="alert">
							 <img src="../image/warning.png" alt="Warning Sign" style="height: auto; width: auto; max-height: 17.5px; max-width: 17.5px;">
				  				
							<strong>Alert!</strong> <?php echo $count . " account has a negative balance!";?>
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
				  				
				  				<?php
				  			}
				  			else if($count > 1){
				  				?>
				  				<img src="../image/warning.png" alt="Warning Sign" style="height: auto; width: auto; max-height: 17.5px; max-width: 17.5px;">
				  				<?php echo $count . " accounts have a negative balance!";
				  			}
				  			else echo "<br>";
				  		?>
				  		<br>
				  		<br>
				  		
				  </p>
						<div class="alert alert-warning alert-dismissible fade show"
							role="alert">
							<strong>Alert!</strong> You should check out your accounts.
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<p class="lead">
							<a class="btn btn-danger btn-lg" href="View/FundingView.php"
								role="button">Go to Funding</a>
						</p>
					</div>
				</div>
			</div>

			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next"
				href="#carouselExampleIndicators" role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>

		</div>

		<br>
		<br>
		<!-- Links to Staff, Inventory, Funding Pages -->
		<div class="row">
    		<div class="col-sm-4">	
				<a href="View/StaffView.php">
				  <img class="card-img-top menu" src="../image/Personnel_Red.png" alt="Card image cap">
				</a>
  			</div>
  			
  			<div class="col-sm-4">
				<a href="View/InventoryView.php">
				  <img class="card-img-top menu" src="../image/Utility_Red.png" alt="Card image cap">
				</a>
   			</div>
   			
   			<div class="col-sm-4">
				<a href="View/FundingView.php">
				  <img class="card-img-top menu" src="../image/Fiance_Red.png" alt="Card image cap">
				</a>
  			</div>
  		</div>
	</div>

	<!-- Footer -->
	<br><br><br>
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
<html />
