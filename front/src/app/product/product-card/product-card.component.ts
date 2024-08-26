import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";

@Component({
	selector: 'app-product-card',
	standalone: true,
	imports: [],
	templateUrl: './product-card.component.html',
})

export class ProductCardComponent {
	constructor(
		private router: Router,
	) {
	}

	@Input() product: any;

	getProductPge() {
		return this.router.navigate(
			['/product'],
			{queryParams: {id: this.product.id}}
		);
	}
}
