<html>
	<head>
		<title>URLMS</title>
		<!-- https://getbootstrap.com/docs/4.0/content/tables/ -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
		<!--  <link rel="stylesheet" type="text/css" href="style/homepagecss.css"> -->
	</head>
	<body background="../image/lab_background.jpg">
<!-- 	<h1 style="text-align: center"><a href="index.php">University Research Lab Management System</a></h1> -->
	
	<!--  Nav Bar -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
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
	      			<li class="nav-item" style="text-decoration: none;">
	      				<a class="nav-link" href=#>Help</a>
	      			</li>
	    		</ul>
  			</div>
	</nav>
	<br><br>
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
  		<!-- Carousel -->
  		<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  		   <ol class="carousel-indicators">
			    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
			    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			  </ol>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <div class="jumbotron" style="text-align: center;">
				  <h1 class="display-3">Staff</h1>
				  <p class="lead">Staff Info Staff Info Staff Info</p>
				  <hr class="my-4">
				  <p>MORE STAFF INFO MORE STAFF INFO MORE STAFF INFO</p>
				  <p class="lead">
				    <a class="btn btn-primary btn-lg" href="View/StaffView.php" role="button">Go to Staff</a>
				  </p>
			  </div>
		    </div>
		    <div class="carousel-item">
		      <div class="jumbotron" style="text-align: center;">
				  <h1 class="display-3">Inventory</h1>
				  <p class="lead">Inventory Info Inventory Info Inventory Info</p>
				  <hr class="my-4">
				  <p>MORE INV INFO MORE INV INFO MORE INV INFO</p>
				  <p class="lead">
				    <a class="btn btn-primary btn-lg" href="View/InventoryView.php" role="button">Go to Inventory</a>
				  </p>
			  </div>
		    </div>
		    <div class="carousel-item">
		      <div class="jumbotron" style="text-align: center;">
				  <h1 class="display-3">Funding</h1>
				  <p class="lead">Funding Info Funding Info Funding Info</p>
				  <hr class="my-4">
				  <p>MORE FUND INFO MORE FUND INFO MORE FUND INFO</p>
				  <p class="lead">
				    <a class="btn btn-primary btn-lg" href="View/FundingView.php" role="button">Go to Funding</a>
				  </p>
			  </div>
		    </div>
		  </div>
		  
		  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>

		</div>
		
		<!-- Links to Staff, Inventory, Funding Pages -->
		<div class="row">
    		<div class="col-sm-4">	
				<div class="card" style="width: 20rem;">
					<a href="View/StaffView.php">
					  <img class="card-img-top" src="../image/staff_logo.jpg" alt="Card image cap">
					</a>
					  <div class="card-body">
						  <a href="View/StaffView.php">
						   	<h2 class="card-text">Staff</h2>
						  </a>
						    <p class="card-text">Description</p>
					  </div>
				</div>
  			</div>
  			
  			<div class="col-sm-4">
  				<div class="card" style="width: 20rem;">
					<a href="View/InventoryView.php">
					  <img class="card-img-top" src="../image/inventory_logo.jpg" alt="Card image cap">
					</a>
					  <div class="card-body">
						  <a href="View/InventoryView.php">
						   	<h2 class="card-text">Inventory</h2>
						  </a>
						    <p class="card-text">Description</p>
					  </div>
				</div>
   			</div>
   			
   			<div class="col-sm-4">
  				<div class="card" style="width: 20rem;">
					<a href="View/FundingView.php">
					  <img class="card-img-top" src="../image/funding_logo.jpg" alt="Card image cap">
					</a>
					  <div class="card-body">
						  <a href="View/FundingView.php">
						   	<h2 class="card-text">Funding</h2>
						  </a>
						    <p class="card-text">Description</p>
					  </div>
				</div>
   			</div>
  		</div>


	</div>
	
	<!-- Footer -->
	<br><br>
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
<html/>
