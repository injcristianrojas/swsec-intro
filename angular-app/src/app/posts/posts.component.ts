import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  posts: Object;
  postForm = new FormGroup({
    mensaje: new FormControl('')
  });
  invalid: boolean;

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts(): void {
    this.api.getPosts().subscribe(
      data => {
        this.posts = data;
      },
      error => {
        console.log(error);
      }
      );
  }

  insertPost(message): void {
    this.api.insertPost(message).subscribe(
      data => {
        this.getPosts();
        this.postForm.reset();
      },
      error => {
        console.log(error);
      }
    );
  }

  onSubmit(): void {
    this.invalid = this.postForm.status == 'INVALID';
    if (!this.invalid)
      this.insertPost(this.postForm.value.mensaje);
  }

}
