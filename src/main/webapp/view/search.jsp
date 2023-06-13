<%@page import="com.example.demo.common.BaseConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.example.demo.domain.Post"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Posts</title>
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	</head>
	<body>
	<script>
                        const urlParams = new URLSearchParams(window.location.search);
                        const message = urlParams.get('message');
                        const status = urlParams.get('status');

                        if (message) {
                            const alertDiv = document.createElement('div');
                            alertDiv.classList.add('alert');
                            alertDiv.classList.add(status === 'success' ? 'alert-success' : 'alert-danger');
                            alertDiv.textContent = message;
                            document.body.appendChild(alertDiv);

                            setTimeout(function() {
                                alertDiv.remove();
                            },5000);
                        }
                    </script>
		<div class="container">
			<div class="d-flex justify-content-between align-items-center mb-3">
				<h2>Posts Managerment System</h2>
				<br/>
				<form action="index" method="GET">
                                <input type="text" name="searchTerm"> <input type="submit" value="Search">
                                </form>
				<a href="/addpost" class="btn btn-primary">Add Post</a>
			</div>

			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th>Title</th>
						<th>Description</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<Post> posts = (List<Post>) request.getAttribute("posts");

					for (Post post : posts) {
					%>
					<tr>
						<td><a href='/postdetails?id=<%=post.getId()%>'><%=post.getTitle()%></a></td>
						<td><%=post.getDescription()%></td>
						<td>
							<form method='GET' action='/postdetails' style="display: inline-block;">
								<input type='hidden' name='id' value='<%=post.getId()%>' />
								<button class='btn btn-info'>Details</button>
							</form>
							<form method='GET' action='/postedit' style="display: inline-block;">
								<input type='hidden' name='id' value='<%=post.getId()%>' />
								<button class='btn btn-warning'>Edit</button>
							</form>
							<form method='POST' action='/postdelete' style="display: inline-block;">
								<input type='hidden' name='id' value='<%=post.getId()%>' />
								<button class='btn btn-danger'>Delete</button>
							</form>
						</td>
					</tr>
					<%}%>
				</tbody>
			</table>
		</div>
		<!-- jQuery, Popper.js, and Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzW
	</body>
</html>
