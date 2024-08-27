import {Component, Input} from '@angular/core';
import {Router} from "@angular/router";
import {GlobalService} from "../../global.service";
import {NgIf} from "@angular/common";

@Component({
	selector: 'app-product-card',
	standalone: true,
	imports: [
		NgIf
	],
	templateUrl: './product-card.component.html',
})

export class ProductCardComponent {
	constructor(
		private router: Router,
		private global: GlobalService,
	) {
	}

	@Input() product: any;

	getProductPge() {
		return this.router.navigate(
			['/product'],
			{queryParams: {id: this.product.id}}
		);
	}

	public getRole() {
		return this.global.role;
	}

	public checkOwner() {
		return this.global.role === 'SELLER' && this.global.userid == this.product.ownerId;
	}
}
