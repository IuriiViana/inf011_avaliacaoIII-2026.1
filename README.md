# INF011 - Padrões de Projeto - Avaliação III
## O Ecossistema de Streaming: Do Motor Técnico à Plataforma Comercial

---

## Questão I — Padrões: **Composite** + **Builder**

### 1. Composite (GoF — Estrutural)

**Justificativa:**  
O enunciado exige que o carrinho de compras trate filmes avulsos e pacotes aninhados "exatamente da mesma maneira". Essa é a definição de livro-texto do Composite: compor objetos em árvores parte-todo, permitindo que o cliente opere sobre folhas e composições de forma uniforme. A alternativa (verificar instanceof em todo lugar) violaria OCP e tornaria o carrinho frágil.

**Participantes:**

| Papel no padrão | Classe / Interface |
|---|---|
| **Component** | `br.edu.ifba.inf011.avaliacao3.composite.ProdutoComercial` |
| **Leaf** | `br.edu.ifba.inf011.model.comercial.Filme` |
| **Leaf** | `br.edu.ifba.inf011.model.comercial.Episodio` |
| **Composite** | `br.edu.ifba.inf011.model.comercial.Pacote` |
| **Composite** (intermediário) | `br.edu.ifba.inf011.model.comercial.Serie` |
| **Client** | `br.edu.ifba.inf011.model.ClienteAval3` (via carrinho/Playlist) |

**Como funciona:**  
`ProdutoComercial` define `getPreco()` e `getDuracao()`. `Filme` e `Episodio` respondem com seus próprios valores. `Pacote` itera sobre `List<ProdutoComercial>` somando recursivamente — um `Pacote` dentro de outro `Pacote` funciona sem nenhuma lógica especial. O carrinho chama `getPreco()` em qualquer `ProdutoComercial` sem saber se é uma folha ou uma árvore de 10 níveis.

---

### 2. Builder (GoF — Criacional)

**Justificativa:**  
A primeira versão criava pacotes com construtores encadeados gigantescos (`new Pacote("SciFi", new Pacote(...), new Serie(...), new Filme(...))`), tornando a criação de promoções propícia a erros e ilegível. O Builder separa a construção passo a passo da representação final, produzindo uma API fluente que é lida como uma especificação declarativa.

**Participantes:**

| Papel no padrão | Classe |
|---|---|
| **Builder** (e Director embutido) | `br.edu.ifba.inf011.avaliacao3.builder.PacoteBuilder` |
| **Product** | `br.edu.ifba.inf011.model.comercial.Pacote` |
| **Client** | `br.edu.ifba.inf011.model.ClienteAval3` |

**Como funciona:**  
`PacoteBuilder` acumula estado via métodos encadeados (`comNome()`, `comDesconto()`, `adicionar()`) e só constrói o `Pacote` quando `build()` é chamado. O construtor de `Pacote` fica encapsulado — o cliente nunca instancia `Pacote` diretamente.

---

## Questão II — Padrão: **Visitor**

**Justificativa:**  
A equipe de Ciência de Dados adicionou `getBandwidth()`, `toXML()` e um relatório de nomes diretamente nas classes `MP3`, `Video`, `Episodio` e `Filme`. A cada nova operação, todas as classes eram modificadas — violação direta de OCP (Aberto/Fechado) e SRP (Responsabilidade Única). O Visitor separa as operações das estruturas de dados: uma nova análise = uma nova classe, sem tocar nas classes existentes.

**Participantes:**

| Papel no padrão | Classe / Interface |
|---|---|
| **Visitor** | `br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor` |
| **ConcreteVisitor** | `br.edu.ifba.inf011.avaliacao3.visitor.LarguraBandaVisitor` |
| **ConcreteVisitor** | `br.edu.ifba.inf011.avaliacao3.visitor.RelatorioNomesVisitor` |
| **ConcreteVisitor** | `br.edu.ifba.inf011.avaliacao3.visitor.ExportadorXmlVisitor` |
| **Element** | `br.edu.ifba.inf011.model.playlist.PlaylistItem` |
| **ConcreteElement** | `br.edu.ifba.inf011.model.playlist.MP3` |
| **ConcreteElement** | `br.edu.ifba.inf011.model.playlist.Video` |
| **ConcreteElement** | `br.edu.ifba.inf011.model.comercial.Filme` |
| **ConcreteElement** | `br.edu.ifba.inf011.model.comercial.Episodio` |
| **ConcreteElement** | `br.edu.ifba.inf011.model.comercial.Serie` |
| **ConcreteElement** | `br.edu.ifba.inf011.model.comercial.Pacote` |
| **ObjectStructure** | `br.edu.ifba.inf011.model.playlist.Playlist` |

**Como funciona:**  
`PlaylistItem` agora possui apenas `accept(PlaylistVisitor)`. Cada `ConcreteElement` implementa `accept()` chamando `visitor.visit(this)` — o double-dispatch garante que o método `visit()` correto seja invocado para cada tipo. Para elementos compostos (`Serie`, `Pacote`), o Visitor navega os filhos chamando `accept(this)` recursivamente, integrando naturalmente com o padrão Composite da Questão I.

**Integração Composite + Visitor:**  
`ProdutoComercial extends PlaylistItem` — todo produto comercial é automaticamente um item de playlist. Isso permite que `Pacote` e `Serie` sejam adicionados diretamente a uma `Playlist`, e o Visitor percorre a árvore Composite inteira com um único `playlist.accept(visitor)`.

---

## Arquivos modificados vs. criados

| Arquivo | Status |
|---|---|
| `avaliacao3/composite/ProdutoComercial.java` | ✅ Criado |
| `avaliacao3/builder/PacoteBuilder.java` | ✅ Criado |
| `avaliacao3/visitor/PlaylistVisitor.java` | ✅ Criado |
| `avaliacao3/visitor/LarguraBandaVisitor.java` | ✅ Criado |
| `avaliacao3/visitor/RelatorioNomesVisitor.java` | ✅ Criado |
| `avaliacao3/visitor/ExportadorXmlVisitor.java` | ✅ Criado |
| `model/playlist/PlaylistItem.java` | 🔧 Refatorado |
| `model/playlist/MP3.java` | 🔧 Refatorado |
| `model/playlist/Video.java` | 🔧 Refatorado |
| `model/playlist/Playlist.java` | 🔧 Refatorado |
| `model/comercial/Filme.java` | 🔧 Refatorado |
| `model/comercial/Episodio.java` | 🔧 Refatorado |
| `model/comercial/Serie.java` | 🔧 Refatorado |
| `model/comercial/Pacote.java` | 🔧 Refatorado |
| `model/ClienteAval3.java` | 🔧 Atualizado |
