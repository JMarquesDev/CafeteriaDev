import { useState, useEffect } from 'react'
import './App.css'

function App() {
  const [clientes, setClientes] = useState([]);
  const [pedidos, setPedidos] = useState([]);
  const [clienteForm, setClienteForm] = useState({ nome: '', email: '' });
  const [pedidoForm, setPedidoForm] = useState({ descricao: '', valorTotal: '', clienteId: '' });

  const API_URL = "http://localhost:8080/api";

  useEffect(() => {
    carregarDados();
  }, []);

  const carregarDados = async () => {
    try {
      const [resC, resP] = await Promise.all([
        fetch(`${API_URL}/clientes`),
        fetch(`${API_URL}/pedidos`)
      ]);
      setClientes(await resC.json());
      setPedidos(await resP.json());
    } catch (err) {
      console.error("Erro ao conectar com a API:", err);
    }
  };

  const cadastrarCliente = async (e) => {
    e.preventDefault();
    const res = await fetch(`${API_URL}/clientes`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(clienteForm)
    });
    if (res.ok) {
      alert("Cliente salvo com sucesso!");
      setClienteForm({ nome: '', email: '' });
      carregarDados();
    }
  };

  const cadastrarPedido = async (e) => {
    e.preventDefault();
    const res = await fetch(`${API_URL}/pedidos/${pedidoForm.clienteId}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        descricao: pedidoForm.descricao,
        valorTotal: parseFloat(pedidoForm.valorTotal)
      })
    });
    if (res.ok) {
      alert("Pedido registrado!");
      setPedidoForm({ descricao: '', valorTotal: '', clienteId: '' });
      carregarDados();
    }
  };

  const formatBRL = (v) =>
    Number(v).toLocaleString("pt-BR", { style: "currency", currency: "BRL" });

  return (
    <>
      <header className="hero-banner">
        <div className="hero-content">
          <div className="hero-text">
            <h1><span>Gestão</span></h1>
            <p>Aroma, sabor e organização em cada pedido.</p>
          </div>
        </div>
      </header>

      <main className="container">
        <div className="grid">
          {/* CLIENTE */}
          <section className="card">
            <div className="card-header">
              <span className="card-header-icon">👥</span>
              <h2>Novo Cliente</h2>
            </div>
            <form onSubmit={cadastrarCliente}>
              <div className="form-group">
                <label htmlFor="nome">Nome</label>
                <input
                  id="nome"
                  placeholder="Ex: Maria Silva"
                  value={clienteForm.nome}
                  onChange={e => setClienteForm({ ...clienteForm, nome: e.target.value })}
                  required
                />
              </div>
              <div className="form-group">
                <label htmlFor="email">E-mail</label>
                <input
                  id="email"
                  type="email"
                  placeholder="maria@email.com"
                  value={clienteForm.email}
                  onChange={e => setClienteForm({ ...clienteForm, email: e.target.value })}
                  required
                />
              </div>
              <button type="submit">+ Cadastrar</button>
            </form>
          </section>

          {/* PEDIDO */}
          <section className="card">
            <div className="card-header">
              <span className="card-header-icon">🛍️</span>
              <h2>Novo Pedido</h2>
            </div>
            <form onSubmit={cadastrarPedido}>
              <div className="form-group">
                <label htmlFor="cliente">Cliente</label>
                <select
                  id="cliente"
                  value={pedidoForm.clienteId}
                  onChange={e => setPedidoForm({ ...pedidoForm, clienteId: e.target.value })}
                  required
                >
                  <option value="">
                    {clientes.length === 0 ? "Cadastre um cliente primeiro" : "Selecione um cliente"}
                  </option>
                  {clientes.map(c => (
                    <option key={c.id} value={c.id}>{c.nome}</option>
                  ))}
                </select>
              </div>
              <div className="form-group">
                <label htmlFor="descricao">Descrição</label>
                <input
                  id="descricao"
                  placeholder="Ex: 2x Cappuccino, 1x Croissant"
                  value={pedidoForm.descricao}
                  onChange={e => setPedidoForm({ ...pedidoForm, descricao: e.target.value })}
                  required
                />
              </div>
              <div className="form-group">
                <label htmlFor="valor">Valor (R$)</label>
                <input
                  id="valor"
                  type="number"
                  step="0.01"
                  min="0"
                  placeholder="0,00"
                  value={pedidoForm.valorTotal}
                  onChange={e => setPedidoForm({ ...pedidoForm, valorTotal: e.target.value })}
                  required
                />
              </div>
              <button type="submit">+ Lançar Pedido</button>
            </form>
          </section>
        </div>

        {/* HISTÓRICO */}
        <section className="card table-section">
          <div className="card-header">
            <h2>Histórico de Pedidos</h2>
            <span className="card-meta">
              {pedidos.length} {pedidos.length === 1 ? "pedido" : "pedidos"}
            </span>
          </div>

          {pedidos.length === 0 ? (
            <div className="empty-state">
              <div className="empty-state-icon">☕</div>
              <p>Nenhum pedido ainda, lance o primeiro!</p>
            </div>
          ) : (
            <div className="table-wrapper">
              <table>
                <thead>
                  <tr>
                    <th>Cliente</th>
                    <th>Descrição</th>
                    <th className="text-right">Valor</th>
                  </tr>
                </thead>
                <tbody>
                  {pedidos.map(p => (
                    <tr key={p.id}>
                      <td className="cliente-name">
                        {p.cliente?.nome || p.clienteNome || '—'}
                      </td>
                      <td>{p.descricao}</td>
                      <td className="text-right valor">{formatBRL(p.valorTotal)}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </section>

        <footer>☕ Café Dev · {new Date().getFullYear()}</footer>
      </main>
    </>
  );
}

export default App;