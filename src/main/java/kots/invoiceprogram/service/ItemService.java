package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.Item;
import kots.invoiceprogram.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    //TODO: Poprawić na lepszy update
    public Set<Item> updateItemList(Invoice invoice, Set<Item> itemList) {
        repository.removeAllByInvoice(invoice);

        for(Item item : itemList) {
            item.setInvoice(invoice);
        }

        return itemList;
    }
}
