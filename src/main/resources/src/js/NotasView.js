export default class NotasView {
    constructor(root, { notaSelecionada, notaAdicionada, notaEditada, notaDeletada } = {}) {//root é o contexto o app
        this.root = root;
        this.notaSelecionada = notaSelecionada;
        this.notaAdicionada = notaAdicionada;
        this.notaEditada = notaEditada;
        this.notaDeletada = notaDeletada;
        //aqui embaixo é a parte do HTML Direito 
        this.root.innerHTML = `
            <div class="barra__de__Divisao">
                <button class="botao_Adicionar" type="button">Adicionar Nota</button>
                <div class="notas_lista"></div>
            </div>
            <div class="notas_visualizacao">
                <input class="notas_titulo" type="text" placeholder="Nova nota">
                <textarea class="notas_corpo">sdsfdghfN...</textarea>
            </div>
        `;

        const btnAddNota = this.root.querySelector(".botao_Adicionar");
        const pegaTitulo = this.root.querySelector(".notas_titulo");
        const pegaDescricao = this.root.querySelector(".notas_corpo");

        if (btnAddNota) {// verificação se o botão é nulo 
            btnAddNota.addEventListener("click", () => {// quando clicado aciona o metodo de adicionar
                if (this.notaAdicionada) {
                    this.notaAdicionada();
                } else {
                    console.error("A função notaAdicionada não foi passada como parâmetro.");
                }
            });
        } else {
            console.error("O botão Adicionar Nota não foi encontrado.");
        }

        [pegaTitulo, pegaDescricao].forEach(pegaCampo => { // aqui é para a edição da nota fazendo um foreach em todas as notas para verificar qual editar
            pegaCampo.addEventListener("blur", () => {
                const tituloAdicionado = pegaTitulo.value.trim();
                const descricaoAdicionada = pegaDescricao.value.trim();
                try {
                    this.notaEditada(tituloAdicionado, descricaoAdicionada);
                } catch (error) {
                    console.error(`A nota não foi editada ${error}`);
                }
            });
        });

        this.subirVisualNotaVsibilidade(false);//inicialmente a visibiidade e falsa 
    }

    _criarListaDeItemsHTML(id, titulo, corpo, data) {// a view que representa a nota do lado esquerdo
        const TAMANHO_DO_CORPO = 60;
        return `
            <div class="notas_lista-item" data-nota-id="${id}">
                <div class="notas_pequenas-titulo">${titulo}</div>
                <div class="notas_pequenas-corpo">
                    ${corpo.substring(0, TAMANHO_DO_CORPO)}
                    ${corpo.length > TAMANHO_DO_CORPO ? "..." : ""}
                </div>
                <div class="notas_pequenas-salvas">
                    ${new Date(data).toLocaleString(undefined, { dateStyle: "full", timeStyle: "short" })}
                </div>
            </div>
        `;
    }

    subirNotasLista(notas) {//subindo a lista de notas todas 
        const containerListaNotas = this.root.querySelector(".notas_lista");
        containerListaNotas.innerHTML = "";
        for (const nota of notas) {
            const html = this._criarListaDeItemsHTML(nota.id, nota.titulo, nota.corpo || "", nota.salva);
            containerListaNotas.insertAdjacentHTML("beforeend", html);
        }

        containerListaNotas.querySelectorAll(".notas_lista-item").forEach(listaDeItensDeNotas => {//Deletação visual de uma nota na lista com o Clique duplo 
            listaDeItensDeNotas.addEventListener("click", () => {
                this.notaSelecionada(listaDeItensDeNotas.dataset.notaId);
            });

            listaDeItensDeNotas.addEventListener("dblclick", () => {
                const doDelete = confirm("Você quer mesmo Deletar a nota?");
                if (doDelete) {
                    this.notaDeletada(listaDeItensDeNotas.dataset.notaId);
                }
            });
        });
    }

    subirNotasAtivas(nota) {// subir as notas configuradas como ativas 
        this.root.querySelector(".notas_titulo").value = nota.titulo;
        this.root.querySelector(".notas_corpo").value = nota.corpo;

        this.root.querySelectorAll(".notas_lista-item").forEach(listaDeItensDeNotas => {
            listaDeItensDeNotas.classList.remove("notas_lista-item--selecionada");
        });

        this.root.querySelector(`.notas_lista-item[data-nota-id="${nota.id}"]`).classList.add("notas_lista-item--selecionada");
    }

    subirVisualNotaVsibilidade(visible) {// ajusta a visibilidade das notas 
        this.root.querySelector(".notas_visualizacao").style.visibility = visible ? "visible" : "hidden";
    }
}