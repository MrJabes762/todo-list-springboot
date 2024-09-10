export default class NotasAPI {
    static getTodasNotas() {//Metodo Estatico de Classe que pega todas as notas do armazenamento 
        const notas = JSON.parse(localStorage.getItem("aplicativodenotas") || "[]");// pega as notas 
        return notas.sort((a, b) => new Date(b.salva) - new Date(a.salva)); // retorna elas ja ordenadas em uma function
    }

    static salvaNotas(notaPraSalvar) {// Esse metodo Estatico recebe a nota de entrada que deseja salvar e lança ela no banco 
        const notas = NotasAPI.getTodasNotas();
        const alterar = notas.find(nota => nota.id == notaPraSalvar.id);// verifica a existencia de uma nota igual no banco e ja altera ela

        if (alterar) {
            alterar.titulo = notaPraSalvar.titulo;
            alterar.corpo = notaPraSalvar.corpo;
            alterar.salva = new Date().toISOString();
        } else {
            notaPraSalvar.id = Math.floor(Math.random() * 1000000);
            notaPraSalvar.salva = new Date().toISOString();
            notas.push(notaPraSalvar);
        }

        localStorage.setItem("aplicativodenotas", JSON.stringify(notas));
    }

    static deletarNotas(id) {// Esse metodo Estatico deleta todas as notas pelo ID da nota recebido 
        const notas = NotasAPI.getTodasNotas();
        const novaNota = notas.filter(nota => nota.id != id);
        localStorage.setItem("aplicativodenotas", JSON.stringify(novaNota));
    }
}