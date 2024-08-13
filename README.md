/*=v==============================={/controllers}==============================v=*/{
// FILE TYPE = Java Class
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    public ResponseEntity<Produto> inserir(@RequestBody Produto produto) {
        Produto produtoSalva = produtoService.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalva);

    }
}
/*=^==============================={/controllers}==============================^=*/}
/*=v================================={/model}==================================v=*/{
// FILE TYPE = Java Class
@Entity
@Table(name = "^Tabela_N^")
public class ^Tabela_N^ {
	@Id
	@GeneratedValue(Strategy = GenerationType.IDENTITY)
	private ^type^ ^campo_id^
	
	private ^type^ ^campo^
	
	private ^type^ ^campo^
	
	private ^type^ ^campo^
	
	private ^type^ ^campo^
	
	@OneToMany(mappedBy = "^tabela_pai^")
	private List<^Tabela_FILHO^> ^tabela_filho^Lista = new ArrayList<>();
	
	@ManyToOne
    @JoinColumn(name = "^nome_campo^")
	private ^Tabela_PAI^ ^tabela_pai^
	
	//GETTERS AND SETTERS
	
	//EQUALS AND HASHCODE
}
/*=^================================={/model}==================================^=*/}
/*=v=============================={/repositories}==============================v=*/{
// FILE TYPE = Interface
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
/*=^=============================={/repositories}==============================^=*/}
/*=v================================{/services}================================v=*/{
// FILE TYPE = Java Class
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }
}
/*=^================================{/services}================================^=*/}
/*=v================================={POSTMAN}=================================v=*/{
POST : localhost:8080/^tabela_n^
Body:
	Raw --- JSON:
	{
		"^campo^": ^insert^
	}
/*=v================================={POSTMAN}=================================v=*/}
