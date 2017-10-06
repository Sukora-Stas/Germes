module.exports = function(grunt) {

    grunt.initConfig({
        uglify : {
            my_target : {
                files : {
                    'dest/app.min.js' : [ 'js/app.js', 'js/controller.js' ]
                }
            }
        }
    });
    grunt.loadNpmTasks('grunt-contrib-uglify');

    grunt.registerTask('default', [ 'uglify' ]);

};