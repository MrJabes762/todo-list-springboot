
export default class NotasAPI {
    static async getTodasNotas() {
        try {
            const response = await fetch('http://localhost:8080/todos');// vai se conectar com a API nesse endereço 
            if (!response.ok) {
                throw new Error('A conexão não foi feita');
            }
            const todos = await response.json();
            return todos.sort((a, b) => new Date(b.salva) - new Date(a.salva));
        } catch (error) {
            console.error('Erro ao buscar notas:', error);
            return [];
        }
    }

    static async salvaNotas(notaPraSalvar) {
        try {
            const method = notaPraSalvar.id ? 'PUT' : 'POST';
            const url = notaPraSalvar.id ? `http://localhost:8080/todos` : 'http://localhost:8080/todos';
            const response = await fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(notaPraSalvar)
            });
            if (!response.ok) {
                throw new Error('A conexão não foi estabelecida ');
            }
            return await this.getTodasNotas();
        } catch (error) {
            console.error('Erro ao salvar notas:', error);
        }
    }

    static async deletarNotas(id) {
        try {
            const response = await fetch(`http://localhost:8080/todos/${id}`, {
                method: 'DELETE'
            });
            if (!response.ok) {
                throw new Error('A coneção não foi estabelecida ');
            }
            return await this.getTodasNotas();
        } catch (error) {
            console.error('Erro ao deletar notas:', error);
        }
    }
}