# README.MD
*(aka, the CheatSheet)*

**NOTE: ANYTHING BETWEEN §THESE CHARACTERS§ MUST BE REPLACED BY SOMETHING ELSE!!!**
___
# postman
### POST
```text
localhost:8080/§tabela§
Body (Raw --- JSON):
	{
		"§campo§": §VALOR§
	}
```
### GET
#### *Todos*
```text
localhost:8080/§tabela§/todas
```
#### *Unico*
```text
localhost:8080/§tabela§/§id§
```
### DELETE
```text
localhost:8080/§tabela§/§id§
```
___
# File Structure
```
main
|
+>java
| +>br.com.§NOME§.§NOMEPROGRAMA§
| | +>controller
| | +>model
| | +>repositories
| |   +>§tabela§
| |   +>filters
| | +>services
| | +>§NOMEPROGRAMA§Application
|
+>resources
  +>db
  | +>migration
  |   +>V001__§NOME§.sql
  |
  +>application.properties
```
___
# controllers
#### *Java Class*
```
@RestController
@RequestMapping("/§tabela§")
public class §tabela§Controller {

    @Autowired
    private §tabela§Service §tabela§Service;

    @Autowired
    private §Tabela§Repository §tabela§Repository;
    
    @GetMapping("/todas")
    public List<§Tabela§> listarTodas§Tabela§() {
        return §tabela§Repository.findAll(Sort.by("§campo§").ascending());
    }
    @GetMapping("/{id}")
    public ResponseEntity<§Tabela§> buscarPeloCodigo(@PathVariable int id){
        Optional<§Tabela§> §tabela§ = §tabela§Repository.findById(id);
        return §tabela§.isPresent() ? ResponseEntity.ok(§tabela§.get()) : ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity<§Tabela§> inserir(@RequestBody §Tabela§ §tabela§) {
        §Tabela§ §tabela§Salva = §tabela§Service.salvar(§tabela§);
        return ResponseEntity.status(HttpStatus.CREATED).body(§tabela§Salva);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Retorna 204 : No Content
    public void remover(@PathVariable §Tipo§ id){
        §tabela§Repository.deleteById(id);
    }
}
```
___
# model
#### *Java Class*
```
@Entity
@Table(name = "§tabela§")
public class §Tabela§ {
	@Id
	@GeneratedValue(Strategy = GenerationType.IDENTITY);
	private §type§ §campoID§;
	
	private §type§ §campo§;
	
	private §type§ §campo§;
	
	@JsonIgnore
	@OneToMany(mappedBy = "§tabelaPAI§")
	private List<§TabelaFILHO§> §tabelaFILHO§Lista = new ArrayList<>();;
	
	@ManyToOne
    @JoinColumn(name = "§tabelaPAI§_§campoID§")
	private §TabelaPAI§ §tabelaPAI§;
	
	//GETTERS AND SETTERS
	
	//EQUALS AND HASHCODE
}
```
___
# repositories
#### *Interface*
```
@Repository
public interface §Tabela§Repository extends JpaRepository<§Tabela§, §campoID§> {

}
```
___
# repositories . filter
### *Java Class*
```
public class §Tabela§Filter{
    
    private §tipo§ §campo§; // Valores que voce quer buscar.
    
    // GETTERS AND SETTERS
}
```
___
# repositories . §tabela§
### *Interface*
```
public interface §Tabela§RepositoryQuery{
    public Page<§Tabela§> filtrar(§Tabela§Filter §tabela§Filter, Pageable pageable);
}
```
### *Java Class*
```
public class §Tabela§RepositoryImpl implements §Tabela§RepositoryQuery{
  @PersistenceContext
  private EntityManager manager;
  @Override
  public Page<§Tabela§> filtrar(§Tabela§Filter §tabela§Filter, Pageable pageable) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<§Tabela§> criteria = builder.createQuery(§Tabela§.class);
    Root<§Tabela§> root = criteria.from(§Tabela§.class);
    
    Predicate[] predicates = criarRestricoes(§tabela§Filter, builder, root);
    criteria.where(predicates);
    criteria.orderBy(builder.asc(root.get("nome")));
    
    TypedQuery<§Tabela§> query = manager.createQuery(criteria);
    adicionarRestricoesPaginacao(query, pageable);
    return new PageImpl<>(query.getResultList(), pageable, total(§tabela§Filter));
  }
    
  private Long total(§Tabela§Filter §tabela§Filter) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
    Root<§Tabela§> root = criteria.from(§Tabela§.class);
    
    Predicate[] predicates = criarRestricoes(§tabela§Filter, builder, root);
    criteria.where(predicates);
    
    criteria.select(builder.count(root));
    return manager.createQuery(criteria).getSingleResult();
  }
    
  private void adicionarRestricoesPaginacao(TypedQuery<§Tabela§> query, Pageable pageable) {
    int paginalAtual = pageable.getPageNumber();
    int totalRegistrosPorPagina = pageable.getPageSize();
    int primeiroRegistroDaPagina = paginalAtual * totalRegistrosPorPagina;
    
    query.setFirstResult(primeiroRegistroDaPagina);
    query.setMaxResults(totalRegistrosPorPagina);
  }
  
  private Predicate[] criarRestricoes(§Tabela§Filter §tabela§Filter, CriteriaBuilder builder, Root<§Tabela§> root) {
    List<Predicate> predicates = new ArrayList<>();
    if (!StringUtils.isEmpty(§tabela§Filter.getNome())) {
      predicates.add(builder.like(builder.lower(root.get("nome")), "%" + §tabela§Filter.getNome().toLowerCase() + "%"));
    }
    return predicates.toArray(new Predicate[predicates.size()]);
  }
}
```
___
# services
#### *Java Class*
```
@Service
public class §Tabela§Service {

    @Autowired
    private §Tabela§Repository §tabela§Repository;

    public §tabela§ salvar(§Tabela§ §tabela§){
        return §tabela§Repository.save(§tabela§);
    }
}
```
___
# properties
```properties
spring.application.name=§NOMEPROGRAMA§

# Configuracao banco de dados MySQL
spring.jpa.database=MYSQL
spring.datasource.url=jdbc:mysql://localhost:3306/§NOMEPROGRAMA§?createDatabaseIfNotExist=true&useSSL=false&ServerTimezone=America/Sao_Paulo
spring.datasource.username=root
spring.datasource.password=
spring.flyway.baseline-on-migration=true

# Configuracao do Hibernate
spring.jpa.show-sql=true
```
