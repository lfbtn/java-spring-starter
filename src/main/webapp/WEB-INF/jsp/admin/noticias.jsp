<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- <c:url value="/resources/" /> -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Notícias <small>Cadastro de Notícias</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Administração</a></li>
			<li class="active">Notícias</li>
		</ol>
	</section>
	<!-- Main content -->
	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Notícias</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<!-- .box-body -->
					<table id="example1" class="table table-bordered table-striped">
						<thead>
							<tr>
								<th>Id</th>
								<th>Título</th>
								<th>Texto</th>
								<th>Inclusão</th>
								<th>Publicação</th>
								<th>Visível</th>
								<th>Imagem</th>
								<th>Categoria</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${news}" var="someNews">
								<tr>
									<td><a href='<spring:url value="/news/${someNews.id}.html"></spring:url>'>${someNews.id}</a></td>
									<td>${someNews.title}</td>
									<td>${someNews.text}</td>
									<td>${someNews.inclusao}</td>
									<td>${someNews.publicacao}</td>
									<td>${someNews.visivel}</td>
									<td>${someNews.imagem}</td>
									<td>${someNews.categoria1} | ${someNews.categoria2}</td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<a href='<spring:url value="/noticia-register.html"></spring:url>'>
						<button class="btn btn-primary">Nova Notícia</button>
					</a>
				</div>
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row --> </section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

