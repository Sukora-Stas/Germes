module.exports = function (grunt) {

    grunt.initConfig({
        uglify: {
            my_target: {
                files: {
                    'dest/app.min.js': ['js/app.js', 'js/controllers.js']
                }
            }
        }
    });
    grunt.loadNpmTasks('grunt-contrib-uglify');

    grunt.registerTask('default', ['uglify']);

};