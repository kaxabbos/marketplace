package com.marketplace.product.img;

import com.marketplace.appUser.UserService;
import com.marketplace.product.Product;
import com.marketplace.product.ProductService;
import com.marketplace.system.exception.BadRequestException;
import com.marketplace.system.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.marketplace.util.Global.saveFile;

@Service
@RequiredArgsConstructor
public class ProductImgService {

    private final ProductImgRepository repository;
    private final ProductService productService;
    private final UserService userService;

    public ProductImg findById(String id) {
        return repository.findById(Long.parseLong(id)).orElseThrow(() -> new ObjectNotFoundException("Не найдено изображение по ИД " + id));
    }

    public Product save(MultipartFile[] files, String productId) {
        Product product = productService.findById(productId);

        userService.checkOwner(product.getOwner().getId());

        if (files == null || files.length == 0) {
            throw new BadRequestException("Некорректное изображение");
        }

        String[] imgs = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            try {
                imgs[i] = saveFile(files[i], "product");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (String img : imgs) {
            repository.save(new ProductImg(img, product));
        }

        return productService.findById(productId);
    }

    public Product delete(String productId, String imgId) {
        ProductImg productImg = findById(imgId);

        userService.checkOwner(productImg.getProduct().getOwner().getId());

        repository.deleteById(productImg.getId());
        return productService.findById(productId);
    }

}
