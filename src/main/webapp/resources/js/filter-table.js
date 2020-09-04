(function ($) {
    $.fn.filterTable = function (id) {

        if (!id) {
            console.error('You must provide filter input ID in options!');
            return null;
        }

        var filterID = id;
        var table = $(this);
        quantidadeRegistrosNaoEncontrados = 0;

        $(filterID).on("keyup", function () {
            var value = $(this).val().toUpperCase();

            table.find('tr').each(function (index) {
                $row = $(this);
                if ($row.find("th").length > 0) return;
                let bool = false;
                $row.find("td").each(function () {
                    $cell = $(this).text().toUpperCase();
                    if ($cell.indexOf(value) > -1) {
                        bool = true;
                    }
                });

                if (bool) {
                    $row.prop("hidden", false);
                }
                else {
                    $row.prop("hidden", true);
                }

            });
            var quantidadeTotalLinhas = table.find("tr").length - 1;
            var quantidadeLinhasDesabilitadas = table.find("tr:hidden").length;
            habilitarMensagemNenhumRegistro(quantidadeTotalLinhas, quantidadeLinhasDesabilitadas);

        });
    }
}
)(jQuery)

function habilitarMensagemNenhumRegistro(qtdTotalLinhas, qtdLinhasDesabilitadas) {
    if (qtdLinhasDesabilitadas === qtdTotalLinhas) {
        $("#msg-nenhum-registro-encontrado").prop("hidden", false);
    } else {
        $("#msg-nenhum-registro-encontrado").prop("hidden", true);
    }
}

