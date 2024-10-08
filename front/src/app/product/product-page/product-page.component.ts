import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../auth/auth.service";
import {GlobalService} from "../../global.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../product.service";
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {NavigateDirective} from "../../navigate.directive";
import {ProductImgService} from "../product.img.service";
import {OrderingService} from "../../ordering/ordering.service";
import {AlertService} from "../../alert/alert.service";

@Component({
	selector: 'app-product-page',
	standalone: true,
	imports: [
		NgIf,
		NgForOf,
		FormsModule,
		NavigateDirective
	],
	templateUrl: './product-page.component.html',
})

export class ProductPageComponent implements OnInit {

	refineMessage: string = '';

	id: number = 0;

	product: any = {
		name: ''
	};

	products: any[] = [];
	count: any;

	constructor(
		private authService: AuthService,
		private global: GlobalService,
		public router: Router,
		private activatedRoute: ActivatedRoute,
		private productService: ProductService,
		private productImgService: ProductImgService,
		private orderingService: OrderingService,
		private alert: AlertService,
	) {
	}

	get productsSorted() {
		let temp = this.products;
		temp = temp.sort((a, b) => a.id < b.id ? 1 : -1);
		return temp;
	}

	ngOnInit(): void {
		this.authService.getUserProfile().add(() => {
			if (this.role === 'NOT') this.router.navigate(['/login']);
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
					} else {
						this.router.navigate(['/login']);
					}
				})
			}).add(() => {
				this.productService.findAllByBind(this.product.bind, this.product.ownerId).subscribe({
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

	get role() {
		return this.global.role;
	}

	public checkOwner() {
		return this.global.role === 'SELLER' && this.global.userid == this.product.ownerId;
	}

	check(id: number) {
		if (id == this.id) {
			return "mb-2 p-2 bg-secondary";
		}
		return "mb-2 p-2";
	}

	productPage(id: number) {
		this.id = id;
		this.product = this.products.find((product) => product.id == id);
	}

	delete() {
		this.productService.delete(this.id).subscribe({
			next: (() => {
				this.router.navigate(['/products'])
			}),
			error: ((e: any) => {
				console.log(e.error);
				this.alert.showAlertMessage(e.error.message);
			})
		})
	}

	active() {
		this.productService.active(this.id).subscribe({
			next: ((res: any) => {
				this.product = res.data;
				this.products = this.products.map((value) => value.id == this.id ? res.data : value);
			}),
			error: ((e) => {
				console.log(e.error);
				this.alert.showAlertMessage(e.error.message);
			})
		})
	}

	refine() {
		this.productService.refine(this.id, this.refineMessage).subscribe({
			next: ((res: any) => {
				this.product = res.data;
				this.products = this.products.map((value) => value.id == this.id ? res.data : value);
				this.refineMessage = '';
			}),
			error: ((e) => {
				console.log(e.error);
				this.alert.showAlertMessage(e.error.message);
			})
		})
	}

	waiting() {
		this.productService.waiting(this.id).subscribe({
			next: ((res: any) => {
				this.product = res.data;
				this.products = this.products.map((value) => value.id == this.id ? res.data : value);
			}),
			error: ((e) => {
				console.log(e.error);
				this.alert.showAlertMessage(e.error.message);
			})
		})
	}

	archive() {
		this.productService.archive(this.id).subscribe({
			next: ((res: any) => {
				this.product = res.data;
				this.products = this.products.map((value) => value.id == this.id ? res.data : value);
			}),
			error: ((e) => {
				console.log(e.error);
				this.alert.showAlertMessage(e.error.message);
			})
		})
	}

	updateImg(event: any) {
		this.productImgService.save(this.id, event.target.files).subscribe({
			next: ((res: any) => {
				this.product = res.data;
				this.products = this.products.map((value) => value.id == this.id ? res.data : value);
			}),
			error: ((e) => {
				console.log(e.error);
				this.alert.showAlertMessage(e.error.message);
			})
		})
	}

	deleteImg(id: number) {
		this.productImgService.delete(this.id, id).subscribe({
			next: ((res: any) => {
				this.product = res.data;
				this.products = this.products.map((value) => value.id == this.id ? res.data : value);
			}),
			error: ((e) => {
				console.log(e.error);
				this.alert.showAlertMessage(e.error.message);
			})
		})
	}

	ordering() {
		this.orderingService.save(this.count, this.id).subscribe({
			next: (() => {
				this.alert.showAlertMessage("Заказ оформлен");
			}),
			error: ((e) => {
				console.log(e.error);
				this.alert.showAlertMessage(e.error.message);
			})
		})
	}
}
