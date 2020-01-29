<template>
  <div class="hello">
    
  </div>
</template>

<script>
  // $('.accordion-item .heading').on('click', function(e) {
  //   e.preventDefault();

  //   // Add the correct active class
  //   if($(this).closest('.accordion-item').hasClass('active')) {
  //       // Remove active classes
  //       $('.accordion-item').removeClass('active');
  //   } else {
  //       // Remove active classes
  //       $('.accordion-item').removeClass('active');

  //       // Add the active class
  //       $(this).closest('.accordion-item').addClass('active');
  //   }

  //   // Show the content
  //   var $content = $(this).next();
  //   $content.slideToggle(100);
  //   $('.accordion-item .content').not($content).slideUp('fast');
// });
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>