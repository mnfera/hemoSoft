$('#ModalExcluir').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var idDoador = button.data('id');
	var nomeDoador = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-id');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + idDoador);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + nomeDoador + '</strong>?');
});
