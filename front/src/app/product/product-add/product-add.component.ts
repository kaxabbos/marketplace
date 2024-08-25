import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../auth/auth.service";
import {GlobalService} from "../../global.service";
import {Router} from "@angular/router";
import {NgIf} from "@angular/common";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";

@Component({
	selector: 'app-product-add',
	standalone: true,
	imports: [
		NgIf,
		ReactiveFormsModule,
		FormsModule
	],
	templateUrl: './product-add.component.html',
})

export class ProductAddComponent implements OnInit {
	productForm = new FormGroup({
		name: new FormControl("", [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		bind: new FormControl("", [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		category: new FormControl("", [Validators.required, Validators.minLength(1), Validators.maxLength(255)]),
		count: new FormControl("", [Validators.required, Validators.min(1), Validators.maxLength(1000)]),
		price: new FormControl("", [Validators.required, Validators.min(0.01), Validators.maxLength(1000)]),
		description: new FormControl("", [Validators.required, Validators.minLength(1), Validators.maxLength(5000)]),
	})


	message: string = '';


	constructor(
		private authService: AuthService,
		private global: GlobalService,
		public router: Router,
	) {
	}

	ngOnInit(): void {
		this.authService.getUserProfile().add(() => {
			if (this.global.role !== 'SELLER') this.router.navigate(['/login']);
		});
	}

	save() {
		console.log(this.productForm.value.category)
	}
}
