1- Split the uri into plugin, path and query.

Query example:`fiji:openBDV/subpath?foo=bar+address=place`

"fiji://openBDV/s3://janelia-cosem-datasets/jrc_mus-hippocampus-1/jrc_mus-hippocampus-1.n5?foo=bar+address=place";
fiji:openBDV/s3://janelia-cosem-datasets/jrc_mus-hippocampus-1/jrc_mus-hippocampus-1.n5?foo=bar+address=place

2- Create plugin type decorator URLOperation
