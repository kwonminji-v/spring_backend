package com.jpabook.jpashop.service;


import com.jpabook.jpashop.item.Item;
import com.jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /** @Transactional 이 readonly = true여서 하단 코드가 저장이 되지 않기 때문에 Overriding */
    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }
    /**
     상품 서비스는 상품 리포지토리에 단순하게 위임만 하는 클래스
     Item 리포지토리에 바로 접근하여 사용해도 괜
     */

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
