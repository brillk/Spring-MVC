package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;


    //상품 목록
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    // 상품 상세
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    //상품 등록 폼의 URL과 실제 상품 등록을 처리하는 URL을 똑같이 맞추고 HTTP 메서드로 두 기능을
    //구분한다.
    //상품 등록 폼: GET /basic/items/add
    //상품 등록 처리: POST /basic/items/add

    //상품 등록 폼
    @GetMapping("/add") //등록해서 마치면
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add") // 이제 저장시킨다
    public String save() {
        return "basic/addForm";
    }


    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 2));
        itemRepository.save(new Item("itemB", 1000, 7));
    }



}
