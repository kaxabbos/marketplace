import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../auth/auth.service";
import {GlobalService} from "../../global.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../product.service";
import {NgForOf, NgIf} from "@angular/common";

@Component({
	selector: 'app-product-page',
	standalone: true,
	imports: [
		NgIf,
		NgForOf
	],
	templateUrl: './product-page.component.html',
})

export class ProductPageComponent implements OnInit {
	constructor(
		private authService: AuthService,
		private global: GlobalService,
		public router: Router,
		private activatedRoute: ActivatedRoute,
		private productService: ProductService,
	) {
	}

	id: any;
	product: any = {
		name: ''
	};

	products: any[] = []

	ngOnInit(): void {
		this.authService.getUserProfile().add(() => {
			if (this.getRole() === 'NOT') this.router.navigate(['/login']);
		});

		this.activatedRoute.queryParams.subscribe(params => {
			this.id = params['id'];

			this.productService.findById(this.id).subscribe({
				next: ((res: any) => {
					this.product = res.data;
				}),
				error: ((e: any) => {
					console.log(e.error);
					if (e.error.code == 404) {
						this.router.navigate(
							['/error'],
							{queryParams: {message: e.error.message}}
						);
					}
				})
			}).add(() => {
				this.productService.findAllByBind(this.product.bind).subscribe({
					next: ((res: any) => {
						this.products = res.data;
					}),
					error: ((e: any) => {
						console.log(e.error);
					})
				})
			})
		})
	}

	public getRole() {
		return this.global.role;
	}

	public checkOwner() {
		return this.global.role === 'SELLER' && this.global.userid == this.product.ownerId;
	}

	productUpdatePage() {
		this.router.navigate(['/productUpdate'], {queryParams: {id: this.id}});
	}
}
