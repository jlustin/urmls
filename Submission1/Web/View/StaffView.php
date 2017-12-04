<?php $my_dir = dirname(__FILE__);
	require_once $my_dir . '/../persistence/persistence.php';
	require_once $my_dir . '/../model/URLMS.php';
	require_once $my_dir . '/../model/Lab.php';
	require_once $my_dir . '/../model/StaffMember.php';
	require_once $my_dir . '/../model/ResearchRole.php';
	require_once $my_dir . '/../model/ResearchAssociate.php';
	require_once $my_dir . '/../model/ResearchAssistant.php';
	require_once $my_dir . '/../model/ProgressUpdate.php';
?>

<html>
	<head>
		<title>URLMS - Staff</title>
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../style/TableView.css">
		
	</head>
	<body>
	<div class="container">
		<!--  <nav class="navbar navbar-dark bg-dark"> -->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  		<a class="navbar-brand" href="../index.php">
	  			<img src="../image/URLMS_Logo.png" width="40" height="40" class="d-inline-block align-top" alt="">
	  			URLMS
	  		</a>
	 		 <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	  			 <span class="navbar-toggler-icon"></span>
	 		 </button>
	  			<div class="collapse navbar-collapse" id="navbarNav">
		    		<ul class="navbar-nav">
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
	  			</div>
		</nav>
		
		
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
  			
	
		<form action="StaffRequest.php" method="get">
			<br>
			<h3>View Staff List</h3>
			<input type="hidden" name="action" value="9/10" />
 			<input type="submit" class="btn btn-primary" value="View Staff!" />
 			<br>
		</form>
		
		<form action="StaffRequest.php" method="get">
			<br>
			<h3>Add Staff Member</h3>
			<input type="hidden" name="action" value="10/10" />
			<div class="row">
				<div class="col-sm-6">
 					<label for="newStaffName">Name</label>
					<input type="text" class="form-control" name="newstaffname" id="newStaffName" placeholder="Enter staff name"/>
				</div>
			</div>
			
 			<input type="submit" class="btn btn-primary" value="Add staff!" />
 			<br>
		</form>
		
		<form action="StaffRequest.php" method="get">
			<div class="form-group">
			<br>
			<h3>Remove Staff Member</h3>
			<input type="hidden" name="action" value="11/10" />
			<div class="row">
				<div class="col-sm-6">
					<label for="oldStaffName">Name</label>
					<input type="text" class="form-control" name="oldstaffname" id="oldStaffName" placeholder="Enter staff name"/>
				</div>
				<div class="col-sm-6">
					<label for="oldStaffID">ID</label>
					<input type="text" class="form-control" name="oldstaffid" id="oldStaffID" placeholder="Enter staff ID"/>
 				</div>
 			</div>
 			<input type="submit" class="btn btn-primary" value="Remove staff!" />
 			<br>
 			</div>
		</form>
		
		
		<form action="StaffRequest.php" method="get">
			<br>
			<h3>View and Edit Staff Member Record</h3>
			<input type="hidden" name="action" value="12/10" />
			<div class="row">
				<div class="col-sm-6">
					<label for="staffName">Name</label>
					<input type="text" class="form-control" name="staffname" id="staffName" placeholder="Enter staff name"/>
				</div>
				<div class="col-sm-6">
					<label for="staffID">ID</label>
					<input type="text" class="form-control" name="staffid" id="staffID" placeholder="Enter staff ID"/>
				</div>
			</div>
 			<input type="submit" class="btn btn-primary" value="View record!" />
 			<br>
		</form>
		<br>
		<a href="../index.php">Back to homepage</a>

		<br><br>
		<div class="container-fluid">
		<table class="table table-hover" style="width: 100%;">
			
			<thread>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Role(s)</th>
				<th>Progress Updates</th>
			</tr>
			</thread>
			<tbody>
			<?php 
			
			$urlms = (new Persistence())->loadDataFromStore();
			
			
			foreach ($urlms->getLab_index(0)->getStaffMembers() as $member){
				$roles = "";
				if($member->hasResearchRoles()){
					foreach ($member->getResearchRoles() as $r){
						$roles = $roles . get_class($r) . "<br>";
					}
				}else{$roles = "None";}
				
				if($member->hasProgressUpdates()){
					$progress = $member->getProgressUpdate_index(sizeof($member->getProgressUpdates())-1)->getDescription();
					if(strlen($progress)>50){
						$progress = substr($progress, 0, 50) . "...";
					}
				}else{$progress = "None";}
				
				echo "<tr><td>" . $member->getId() . "</td>
					<td><button type=\"button\" class=\"btn btn-outline-primary\">" . $member->getName() . "</button></td>
					<td>". $roles ."</td>
					<td>" . $progress . "</td>
					</tr>";
			}?>
			</tbody>
		</table>
		</div>
		<br><br>
	<!--  	
	<div class="btn-group" role="group" aria-label="Basic example">
	  <button type="button" class="btn btn-secondary">Left</button>
	  <button type="button" class="btn btn-secondary">Middle</button>
	  <button type="button" class="btn btn-secondary">Right</button>
	</div>
	-->
	
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
</div>	
</body>
</html>

