<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <c:url value="/resources/" /> -->
<style type="text/css">
select {
	width: 100%;
}

.controls {
	width: 100%;
	align: center;
	text-align: center;
}

.controls a {
	/*         background-color: #ff0000; */
	background-color: #3385ff;
	border-radius: 4px;
	border: 2px solid #ff;
	color: #ffffff;
	padding: 2px;
	font-size: 14px;
	text-decoration: none;
	display: inline-block;
	text-align: center;
	margin: 5px;
	width: 30px;
}
</style>
<script>
	function moveAll(from, to) {
		$('#' + from + ' option').remove().appendTo('#' + to);
	}

	function moveSelected(from, to) {
		$('#' + from + ' option:selected').remove().appendTo('#' + to);
	}
	function selectAll(from, to) {
		$("select option").attr("selected", "selected");
		$('#' + from + ' option:selected').remove().appendTo('#' + to);
	}
</script>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Notícias <small>Cadastro de Notícias</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i>
					Administração</a></li>
			<li class="active">Notícias</li>
		</ol>
	</section>
	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-8">
				<!-- general form elements -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">Cadastro de Notícias</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->
					<form role="form" method="POST">
						<div class="box-body">
							<div class="form-group col-xs-12">
								<label for="name">Título</label> <input type="text"
									class="form-control" name="title" id="title"
									placeholder="Título" required>
							</div>
							<div class="form-group col-xs-12">
								<label for="name">Texto</label> <textarea 
									class="form-control" name="text" id="text"
									placeholder="Texto" required></textarea>
							</div>
							
							<div class="form-group col-xs-6">
								<label for="name">Publicação</label> <input type="text"
									class="form-control" name="publicacao" id="publicacao"
									placeholder="dd/mm/aaaa" required>
							</div>
							<div class="form-group col-xs-6">
								<label for="name">Agendar Remoção</label> <input type="text"
									class="form-control" name="remocao" id="remocao"
									placeholder="dd/mm/aaaa" required>
							</div>
							<div class="form-group col-xs-6">
								<label for="name">Visibilidade</label> <input type="text"
									class="form-control" name="visivel" id="visivel"
									placeholder="" disabled="disabled">
							</div>
							<div class="form-group col-xs-6">
								<label for="name">Autor</label> <input type="text"
									class="form-control" name="autor" id="autor"
									placeholder="Autor" required>
							</div>
							<div class="form-group col-xs-6">
								<label for="name">Fotógrafo</label> <input type="text"
									class="form-control" name="fotografo" id="fotografo"
									placeholder="Fotógrafo" required>
							</div>
							
							<div class="form-group col-xs-6">
								<label for="name">Link Imagem</label> <input type="text"
									class="form-control" name="imagem" id="imagem"
									placeholder="http://" required>
							</div>
							
							<div class="form-group col-xs-6">
								<label for="name">Link Imagem</label> <input type="text"
									class="form-control" name="imagem" id="imagem"
									placeholder="http://" required>
							</div>
							
							<div class="form-group col-xs-6">
								<label for="name">Categoria 1</label> <input type="text"
									class="form-control" name="categoria1" id="categoria1"
									placeholder="Categoria" required>
							</div>
							
							<div class="form-group col-xs-6">
								<label for="name">Categoria 2</label> <input type="text"
									class="form-control" name="categoria2" id="categoria2"
									placeholder="Categoria 2" required>
							</div>
							
							<div class="form-group col-xs-6">
								<label for="name">Categoria 3</label> <input type="text"
									class="form-control" name="categoria3" id="categoria3"
									placeholder="Categoria 3" required>
							</div>

							</div>
						</div>
						<!-- /.box-body -->
						<div class="box-body">
							<button type="submit" class="btn btn-primary">Cadastrar
								Notícia</button>
						</div>
					</form>
				</div>
				<!-- /.box -->
			</div>
			<!-- /. col-md-6 -->
		</div>
		<!-- /.row -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

