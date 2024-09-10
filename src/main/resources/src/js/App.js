import NotasAPI from "./NotasAPI.js";// o que compoe o app é a API - que conecta o front e o BackEnd
import NotasView from "./NotasView.js";// A view é justamente a parte visual / front do App

export default class App {
    constructor(root) {
        this.notas = [];
        this.notaAtiva = null;
        this.view = new NotasView(root, this._handlers());
        
        this._atualizarNotas();
    }

    _atualizarNotas() {// sempre que realizar uma operação na lista de notas esse metodo deve sempre ser chamado ao final para atualizar
        const notas = NotasAPI.getTodasNotas();
        this._setNotas(notas);
        if (notas.length > 0) {
            this._setNotaAtiva(notas[0]);
        }
    }

    _setNotas(notas) {// pega o Array de notas subindo para a lista e "seta" a sua visibilidade 
        this.notas = notas;
        this.view.subirNotasLista(notas);
        this.view.subirVisualNotaVsibilidade(notas.length > 0);// corre um indice para atualizar a nota  
    }

    _setNotaAtiva(nota) {// resebe a nota e torna ela ativa subindo para a lista de notas ativas
        this.notaAtiva = nota;
        this.view.subirNotasAtivas(nota);
    }

    _handlers() {//esse metodo vai retornar todos os objetos que serão passados para a view
        return {
            notaSelecionada: noteId => {//retorna o ID da nota selecionada para manipular posteriomente 
                const notaselecionadaa = this.notas.find(nota => nota.id == noteId);
                this._setNotaAtiva(notaselecionadaa);
            },
            notaAdicionada: () => {// retorna a nota criada para adicionar
                const novaNota = {
                    titulo: "Insira o Titulo",
                    corpo: "Descreva a nota"
                };
                NotasAPI.salvaNotas(novaNota);
                this._atualizarNotas();
            },
            notaEditada: (titulo, corpo) => {// retorna a nota editada, baseando- se pelo ID da nota selecionada
                NotasAPI.salvaNotas({
                    id: this.notaAtiva.id,
                    titulo,
                    corpo
                });
                this._atualizarNotas();
            },
            notaDeletada: noteId => { // retorna a nota a ser deletada baseando-se pelo ID
                NotasAPI.deletarNotas(noteId);
                this._atualizarNotas();
            }
        };
    }
}

const root = document.getElementById('app');// acredito que esa parte funciona como atualizador do APP
const app = new App(root);