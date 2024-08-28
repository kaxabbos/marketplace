import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {NgIf} from "@angular/common";
import {AuthService} from "../../auth/auth.service";
import {GlobalService} from "../../global.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../product.service";
import {CategoryService} from "../../category/category.service";
import {NavigateDirective} from "../../navigate.directive";

@Component({
	selector: 'app-product-update',
	standalone: true,
	imports: [
		FormsModule,
		NgIf,
		ReactiveFormsModule,
		NavigateDirective
	],
	templateUrl: './product-update.component.html',
})

export class ProductUpdateComponent implements OnInit {
	productForm = new FormGroup({
		name: new FormControl("", [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		bind: new FormControl("", [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		category: new FormControl("", [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		count: new FormControl("", [Validators.required, Validators.min(1), Validators.maxLength(1000)]),
		price: new FormControl("", [Validators.required, Validators.min(0.01), Validators.maxLength(1000)]),
		description: new FormControl("", [Validators.required, Validators.minLength(1), Validators.maxLength(5000)]),
	})

	id: any;

	categories: any[] = [];

	message: string = '';

	constructor(
		private authService: AuthService,
		private global: GlobalService,
		public router: Router,
		private productService: ProductService,
		private activatedRoute: ActivatedRoute,
		private categoryService: CategoryService,
	) {
	}

	ngOnInit(): void {
		this.authService.getUserProfile().add(() => {
			if (this.global.role !== 'SELLER') this.router.navigate(['/login']);
		});

		this.activatedRoute.queryParams.subscribe((params) => {
			this.id = params['id'];
		})

		this.productService.findById(this.id).subscribe({
			next: ((res: any) => {
				if (this.global.userid != res.data.ownerId) this.router.navigate(['/products']);

				this.productForm.setValue({
					name: res.data.name,
					bind: res.data.bind,
					category: res.data.category,
					count: res.data.count,
					price: res.data.price,
					description: res.data.description
				})
			}),
			error: ((e: any) => {
				console.log(e.error)
				this.message = e.error.message;
			})
		})

		this.categoryService.categorySubject.subscribe(value => {
			this.categories = value.categories;
		})

		this.categoryService.findAll();
	}

	update() {
		this.productService.update(this.id, this.productForm.value).subscribe({
			next: ((res: any) => {
				this.router.navigate(['/product'], {queryParams: {id: res.data.id}});
			}),
			error: ((e: any) => {
				this.message = e.error.message;
			})
		})
	}

}
