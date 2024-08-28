import {Component, Input} from '@angular/core';
import {GlobalService} from "../../global.service";
import {NgIf} from "@angular/common";
import {NavigateDirective} from "../../navigate.directive";

@Component({
	selector: 'app-product-card',
	standalone: true,
	imports: [
		NgIf,
		NavigateDirective
	],
	templateUrl: './product-card.component.html',
})

export class ProductCardComponent {
	constructor(
		private global: GlobalService,
	) {
	}

	@Input() product: any;

	public getRole() {
		return this.global.role;
	}

	public checkOwner() {
		return this.global.role === 'SELLER' && this.global.userid == this.product.ownerId;
	}
}
